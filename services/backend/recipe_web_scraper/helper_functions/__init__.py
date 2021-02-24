from time import sleep

from webdriver_manager.chrome import ChromeDriverManager
from selenium import webdriver


def create_webdriver():
    options = webdriver.ChromeOptions()
    options.add_experimental_option('useAutomationExtension', False)
    options.add_experimental_option("excludeSwitches", ["enable-automation"])
    options.add_argument("--disable-blink-features=AutomationControlled")
    return webdriver.Chrome(ChromeDriverManager().install(), options=options)


def read_url_to_scrape(filename: str) -> list:
    file_websites = open(filename, 'r')
    tmp = file_websites.readlines()
    file_websites.close()
    return list(map(lambda x: x[:-1], tmp))


def filter_duplicate_urls() -> None:
    for idx in range(1, 3):
        filename = "./data_source_urls/urls_input_{}.txt".format(idx)
        file = open(filename, 'r')
        result = list(set(file.readlines()))
        file.close()
        file = open(filename, 'w')
        file.writelines(result)
        file.close()


def reduce_url_files() -> None:
    file_output = open('./data_source_urls/urls_input.txt', 'w')
    for idx in range(1, 3):
        filename = "./data_source_urls/urls_input_{}.txt".format(idx)
        file_input = open(filename, 'r')
        file_output.writelines(file_input.readlines())
        file_input.close()
    file_output.close()


def extract_data_bbc(file_output, wd):
    sleep(3)
    _ = wd.find_element_by_class_name('social-follow__rss').location_once_scrolled_into_view
    sleep(2)
    file_output.writelines(
        list(
            map(
                lambda x: x.get_attribute('href') + '\n',
                wd.find_elements_by_class_name('standard-card-new__article-title')
            )
        )
    )
