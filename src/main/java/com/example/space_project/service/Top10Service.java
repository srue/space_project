package com.example.space_project.service;

import com.example.space_project.domain.Top10;
import com.example.space_project.domain.Top10Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.apache.http.client.HttpClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class Top10Service {

    private final Top10Repository top10Repository;

    public void saveTop10Keyword() {
        String keyword = "파티룸,연습실,촬영스튜디오,스터디룸,공연장,공유주방,댄스연습실,렌탈스튜디오,회의실,라이브방송,카페,보컬연습실," +
                "호리존,세미나실,컨퍼런스,스몰웨딩,악기연습실,실외촬영,강의실,운동시설,갤러리,녹음실,독립오피스,코워킹오피스,비상주서비스";

        String[] keyword_list = keyword.split(",");

        String searchKeyword;
        for(int i = 0; i < keyword_list.length; i++) {
            searchKeyword = keyword_list[i];
            String response = searchTop10Keyword(searchKeyword);
            try {
                JSONParser jsonParser = new JSONParser();
                Object obj = jsonParser.parse(response);
                JSONObject jsonObject = (JSONObject) obj;

                JSONArray resultList = (JSONArray) jsonObject.get("result");
                System.out.println(resultList.size());
                for (int r = 0; r < resultList.size(); r++) {
                    if (r >= 10) break;
                    JSONObject result = (JSONObject) resultList.get(r);

                    Long spcSeq = (Long) result.get("spcSeq");
                    String spcNm = (String) result.get("spcNm");

                    Top10 top10 = new Top10(searchKeyword, spcSeq, spcNm);
                    top10Repository.save(top10);
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }

    public String searchTop10Keyword(String keyword) {
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        String url = "https://new-api.spacecloud.kr/search/spaces";

        url = url + "?q=" + keyword;
        return restTemplate.getForObject(url, String.class);
    }
}
