import requests
from pandas import DataFrame
from bs4 import BeautifulSoup
import re
from datetime import datetime
import os

date = str(datetime.now())
date = date[:date.rfind(':')].replace(' ', '_')
date = date.replace(':','시') + '분'



query = input('검색 키워드 입력 : ')
news_num = int(input('총 필요한 뉴스기사 수 입력(숫자) : ')) # 숫자만 입력
query = query.replace(' ', '+')


news_url = 'https://search.naver.com/search.naver?where=news&sm=tab_jum&query={}'

req = requests.get(news_url.format(query))
soup = BeautifulSoup(req.text, 'html.parser')


news_dict = {}
idx = 0
cur_page = 1

print()
print('크롤링 중...')


while idx < news_num:
    
    table = soup.find('ul',{'class' : 'list_news'})
    li_list = table.find_all('li', {'id': re.compile('sp_nws.*')})
    area_list = [li.find('div', {'class' : 'news_area'}) for li in li_list]    
    a_list = [area.find('a', {'class' : 'news_tit'}) for area in area_list]
    
    news_dsc_list = [area.find('div', {'class' : 'news_dsc'}) for area in area_list]
    dsc_wrap_list = [news_dsc.find('div', {'class' : 'dsc_wrap'}) for news_dsc in news_dsc_list]
    api_txt_list = [dsc_wrap.find('a', {'class' : 'api_txt_lines dsc_txt_wrap'}) for dsc_wrap in dsc_wrap_list]

    for n, m in zip(a_list[:min(len(a_list), news_num-idx)], api_txt_list[:min(len(api_txt_list), news_num-idx)]):
        news_dict[idx] = {'title' : n.get('title'),
                          'url' : n.get('href'),
                          'dsc' : m.get_text(),
                          'regdate' : 'CURRENT_TIME()'}
        idx += 1

    cur_page += 1

    pages = soup.find('div', {'class' : 'sc_page_inner'})
    next_page_url = [p for p in pages.find_all('a') if p.text == str(cur_page)][0].get('href')
    
    req = requests.get('https://search.naver.com/search.naver' + next_page_url)
    soup = BeautifulSoup(req.text, 'html.parser')



print('크롤링 완료')

print('데이터프레임 변환')
news_df = DataFrame(news_dict).T

folder_path = os.getcwd()
xlsx_file_name = '네이버뉴스_{}_{}.xlsx'.format(query, date)

news_df.to_excel(xlsx_file_name)

print('엑셀 저장 완료 | 경로 : {}\\{}'.format(folder_path, xlsx_file_name))
os.startfile(folder_path)


# 코드 참고 사이트 : https://everyday-tech.tistory.com/entry/%EC%89%BD%EA%B2%8C-%EB%94%B0%EB%9D%BC%ED%95%98%EB%8A%94-%EB%84%A4%EC%9D%B4%EB%B2%84-%EB%89%B4%EC%8A%A4-%ED%81%AC%EB%A1%A4%EB%A7%81python-2%ED%83%84
