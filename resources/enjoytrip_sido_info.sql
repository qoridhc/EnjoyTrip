use enjoytrip;

create table sido_info(
	sido_code int not null unique,
    sido_name_kor varchar(10),
    sido_name_eng varchar(10),
    sido_description varchar(300),
    foreign key (sido_code) references sido(sido_code),
    primary key (sido_code)
)default character set utf8 collate utf8_general_ci;

alter table sido_info
modify column sido_name_eng varchar(30);

INSERT INTO sido_info (sido_code, sido_name_kor, sido_name_eng, sido_description) VALUES
(1, '서울', 'Seoul', '한국의 수도이자 경제, 문화, 정치의 중심지로, 고궁과 현대적 건물이 조화를 이루며, 한강과 남산타워 등 명소가 많은 도시입니다.'),
(2, '인천', 'Incheon', '국제공항이 있는 도시로, 역사적인 차이나타운과 월미도 해변, 송도 센트럴파크 등이 유명하며, 경제자유구역이 발달해 있습니다.'),
(3, '대전', 'Daejeon', '과학과 기술의 중심지로, 대덕연구단지와 엑스포 과학공원이 있으며, 다양한 연구기관과 첨단산업이 밀집해 있는 도시입니다.'),
(4, '대구', 'Daegu', '섬유산업과 전통시장이 발달한 도시로, 팔공산과 근대 골목 투어가 인기이며, 첨단 의료산업과 패션 산업이 성장하고 있습니다.'),
(5, '광주', 'Gwangju', '민주화 운동의 상징적인 도시로, 풍부한 예술과 문화가 공존하며, 국립아시아문화전당과 맛있는 남도음식이 유명한 곳입니다.'),
(6, '부산', 'Busan', '해운대와 광안리 해변이 유명한 항구도시로, 영화제와 해산물 요리, 그리고 부산타워와 자갈치 시장 등이 많은 관광객을 끌어모읍니다.'),
(7, '울산', 'Ulsan', '자동차와 조선업의 중심지로, 태화강 국가정원과 대왕암공원이 아름답고, 산업과 자연이 조화를 이루는 도시입니다.'),
(8, '세종특별자치시', 'Sejong', '정부 행정기관이 이전한 계획도시로, 편리한 교통과 친환경 도시로 성장하고 있으며, 시민들이 살기 좋은 환경을 제공합니다.'),
(31, '경기도', 'Gyeonggi-do', '서울을 둘러싸고 있는 도로, 다양한 산업과 풍부한 관광자원을 보유하고 있으며, DMZ 투어와 자연 휴양림 등이 유명합니다.'),
(32, '강원도', 'Gangwon-do', '산과 바다가 어우러진 자연의 보고로, 동계 스포츠와 청정 자연이 매력적이며, 설악산과 동해안 해변이 인기 있는 관광지입니다.'),
(33, '충청북도', 'Chungcheongbuk-do', '내륙에 위치한 도로, 산과 계곡이 아름답고 전통문화가 잘 보존되어 있으며, 충주호와 속리산 국립공원이 유명합니다.'),
(34, '충청남도', 'Chungcheongnam-do', '서해안에 위치한 도로, 역사적인 유적지와 풍부한 해산물이 유명하며, 서천 국립생태원과 태안 해안국립공원이 있습니다.'),
(35, '경상북도', 'Gyeongsangbuk-do', '경주와 안동 등 역사 유적지가 많고, 전통문화가 풍부한 도입니다. 불국사와 하회마을은 유네스코 세계문화유산으로 지정되어 있습니다.'),
(36, '경상남도', 'Gyeongsangnam-do', '남해안에 위치한 도로, 아름다운 해안선과 다양한 해양 레포츠가 가능하며, 통영과 거제도의 자연경관이 뛰어납니다.'),
(37, '전라북도', 'Jeollabuk-do', '전주를 중심으로 한 전통문화와 미식의 고장으로, 한옥마을이 유명하며, 전주비빔밥과 판소리가 이 지역의 대표 문화입니다.'),
(38, '전라남도', 'Jeollanam-do', '다도해의 아름다움과 다양한 섬들이 매력적인 지역입니다. 여수와 순천만은 자연 생태의 보고로 많은 관광객이 방문합니다.'),
(39, '제주도', 'Jeju', '화산섬으로, 천혜의 자연경관과 독특한 문화가 공존하는 관광명소입니다. 한라산, 성산일출봉, 제주 올레길 등이 유명합니다.');