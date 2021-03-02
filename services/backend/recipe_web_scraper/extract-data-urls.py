import json
import multiprocessing
import sys

from recipe_scrapers import scrape_me
from helper_functions import MeasureTime


def get_data(url: str, send_end):
    print("Scrapping url:{}.".format(url))
    data = scrape_me(url, wild_mode=True)
    send_end.send(data.schema.data)


def main():
    file = open('data_source_urls/urls_input.txt', 'r')
    output_file = open('./result/bulk_data.json', 'w')
    url_list = list(map(lambda x: x[:-1], file.readlines()))
    results = []
    print("[INFO] - Urls were read.")
    for url in range(0, len(url_list), 50):
        jobs = []
        pipe_list = []
        for token in range(url, url+50):
            if token >= len(url_list):
                break
            recv_end, send_end = multiprocessing.Pipe(False)
            process = multiprocessing.Process(target=get_data, args=(url_list[token], send_end, ))
            jobs.append(process)
            pipe_list.append(recv_end)
            process.start()
        for process in jobs:
            process.join()
            print("[INFO] - {} finished.".format(process.name))
        results += [x.recv() for x in pipe_list]
    output_file.writelines(json.dumps(results, indent=4))


if __name__ == "__main__":
    mt = MeasureTime.MeasureTime()
    mt.start()
    main()
    mt.stop()
    print(f"[INFO] - script executed in {mt.get_minutes():0.2f} minutes.")
    sys.exit(0)
