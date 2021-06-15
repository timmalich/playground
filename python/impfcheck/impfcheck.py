# export PATH=$PATH:/home/tmalich/.local/bin
from selenium.webdriver import Firefox
from selenium.webdriver.firefox.options import Options
import webbrowser
import time
opts = Options()
#opts.add_argument("--window-size=1200x600")
opts.headless = True
#assert opts.headless  # Operating in headless mode
try:
    browser = Firefox(options=opts)
    browser.get('https://229-iz.impfterminservice.de/impftermine/service?plz=70629')
    time.sleep(30)
    print('Page must be loaded. Clicking around.')
    browser.find_element_by_class_name('cookies-info-close').click()
    time.sleep(1)
    nocoderadio = browser.find_elements_by_class_name('ets-radio-control')[1]
    nocoderadio.click()
    time.sleep(29)
    no_slot = browser.find_element_by_class_name('alert-danger')
    if not no_slot.text.startswith('Es wurden keine freien Termine'):
        webbrowser.open('https://229-iz.impfterminservice.de/impftermine/service?plz=70629')
except Exception as e:
    print(e)
    webbrowser.open('https://229-iz.impfterminservice.de/impftermine/service?plz=70629')
browser.close()
