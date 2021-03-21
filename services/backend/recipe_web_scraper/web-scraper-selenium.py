import sys
from helper_functions import create_webdriver, read_url_to_scrape, filter_duplicate_urls, reduce_url_files, \
    extract_data_bbc
from helper_functions import MeasureTime

from time import sleep
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.wait import WebDriverWait


def scrape_data_from_ambitiouskitchen() -> None:
    file = open("data_source_urls/urls_input_1.txt", 'w')
    fresh_start = True
    url_to_scrape = read_url_to_scrape('data_source_urls/scrapper_urls_1.txt')
    driver = create_webdriver()
    for url in url_to_scrape:
        driver.get(url)
        if fresh_start:
            WebDriverWait(driver, 5).until(EC.presence_of_element_located((By.TAG_NAME, "iframe")))
            driver.switch_to.frame(driver.find_element_by_class_name('faktor-iframe-wrapper'))
            driver.find_element_by_class_name('solo-button').click()
            driver.switch_to.default_content()
            WebDriverWait(driver, 10).until(
                EC.element_to_be_clickable((By.CLASS_NAME, "popup-click-close-trigger-6")))
            ActionChains(driver).move_by_offset(0, 0).click().perform()
            fresh_start = False
        WebDriverWait(driver, 5).until(EC.presence_of_element_located((By.CLASS_NAME, "adthrive-close")))
        driver.find_element_by_class_name('adthrive-close').click()
        while driver.find_element_by_xpath("//button[contains(@class,'alm-load-more-btn')]").get_attribute(
                'disabled') is None:
            driver.find_element_by_xpath("//button[contains(@class,'alm-load-more-btn')]").click()
            sleep(2)
        titles = driver.find_elements_by_xpath("//h2[contains(@class,'data-title')]")
        for item in titles:
            link_element = item.find_element_by_tag_name('a')
            link = link_element.get_attribute('href')
            file.write(link + '\n')
    file.close()
    driver.quit()


def scrape_data_from_bbcgoodfood() -> None:
    fresh_start = True
    file = open('data_source_urls/urls_input_2.txt', 'w')
    urls_to_scrape = read_url_to_scrape('data_source_urls/scrapper_urls_2.txt')
    driver = create_webdriver()
    for url in urls_to_scrape:
        driver.get(url)
        if fresh_start:
            fresh_start = False
            WebDriverWait(driver, 20) \
                .until(EC.element_to_be_clickable((By.CLASS_NAME, "css-109rcy")))
            driver.find_element_by_class_name("css-109rcy").click()
            WebDriverWait(driver, 20) \
                .until(EC.element_to_be_clickable((By.ID, "im-autoplay-video-ad-close")))
            driver.find_element_by_id("im-autoplay-video-ad-close").click()
            _ = driver.find_element_by_class_name('social-follow__rss').location_once_scrolled_into_view
            WebDriverWait(driver, 20) \
                .until(EC.element_to_be_clickable((By.CLASS_NAME, "ad-banner-dismiss")))
            if len(driver.find_elements_by_class_name("ad-banner-dismiss")):
                driver.find_element_by_class_name("ad-banner-dismiss").click()
            WebDriverWait(driver, 20) \
                .until(EC.presence_of_element_located((By.ID, "optimize-modal-closebtn"))).click()
        while len(driver.find_elements_by_xpath("//a[@aria-label='Next Page']")):
            extract_data_bbc(file, driver)
            WebDriverWait(driver, 20) \
                .until(EC.presence_of_element_located((By.CSS_SELECTOR, "a.pagination-arrow")))
            driver.find_element_by_xpath("//a[@aria-label='Next Page']").click()
        extract_data_bbc(file, driver)
    file.close()
    driver.quit()


if __name__ == "__main__":
    mt = MeasureTime.MeasureTime()
    mt.start()
    scrape_data_from_ambitiouskitchen()
    scrape_data_from_bbcgoodfood()
    filter_duplicate_urls()
    reduce_url_files()
    mt.stop()
    print(f"[INFO] - script executed in {mt.get_minutes():0.2f} minutes.")
    sys.exit(0)
