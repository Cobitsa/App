package com.cobitsa.jarvis.com.cobitsa.jarvis.bus.common;

import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

public class GetPrevStId {

    String key;         // 인증키

    // 키값으로 초기화
    public GetPrevStId(String key) {
        this.key = key;
    }

    // 이전 정류소의 아이디를 반환하는 메소드
    // @param busRouteId : 노선 정보
    // @param stId : 현재 정류소 아이디
    // @return 이전 정류소의 아이디
    public String get(String busRouteId, String stId) {
        String url = "http://ws.bus.go.kr/api/rest/busRouteInfo/getStaionByRoute" +
                "?ServiceKey=" + key +
                "&busRouteId=" + busRouteId;
        String prevStId = "";       // 리턴값
        int preIndex;                     // 이전 정류소 인덱스
        try {
            ParsingXML parsingXML = new ParsingXML(url);
            preIndex = parsingXML.index("station", stId) - 1;
            prevStId = parsingXML.parsing("station", preIndex);
        } catch (ParserConfigurationException | InterruptedException e) {
            e.printStackTrace();
        }

        return prevStId;
    }
}
