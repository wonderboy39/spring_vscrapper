# JSTL 사용한 것들 정리

참고자료 :http://fruitdev.tistory.com/132



# c:if, c:choose, eq, ne, empty

정리할 것 :  

http://fruitdev.tistory.com/131



# 숫자 대소 비교? 예제

```jsp
<c:choose>
    <c:when test="${boardList.size()==0 }">
        <tr>
            <td>---</td>
            <td>현재 등록된 게시물이 없습니다.</td>
            <td>---</td>
        </tr>
    </c:when>
    <c:otherwise>
        <c:forEach var="item" items="${boardList}" varStatus="status">
            <tr>
                <td>${item.seq}</td>
                <td><a href="getBoard.do?seq=${item.seq}">${item.title }
                    </a></td>
                <td>${item.writer}</td>
            </tr>
        </c:forEach>
    </c:otherwise>
</c:choose>
```



  

# forEach + 대소비교



**BoardController.java 중 일부**

```java
@RequestMapping(value="/getBoardList.do")
public ModelAndView getBoardList(
    @RequestParam(value="searchCondition" ,defaultValue="TITLE", required=false) String condition,
    @RequestParam(value="searchKeyword", defaultValue="", required=false) String keyword,
    VBoardVO vo, ModelAndView mav){
    System.out.println("검색조건 : " + condition);
    System.out.println("검색단어 : " + keyword);
    try {
        mav.addObject("boardList", boardDAO.selectVBoardList(vo));	// Model 정보 저장 
    } catch (Exception e) {
        e.printStackTrace();
    }
    mav.setViewName("getBoardList.jsp"); 						// View 정보 저장
    return mav;
}
```

​    

**boardList의 size가 0보다 클때, 또는 0일때 (즉, 게시물이 없는지, 있는지 검사)**

- boardList의 size()가 0보다 클 경우
  forEach var = "valueName" items="boardList" varStatus="status"
- boardList의 size()가 0일 경우
  현재 등록된 게시물이 없습니다.

```jsp
<c:choose>
    <c:when test="${boardList.size()==0 }">
        <tr>
            <td>---</td>
            <td>현재 등록된 게시물이 없습니다.</td>
            <td>---</td>
        </tr>
    </c:when>
    <c:otherwise>
        <c:forEach var="item" items="${boardList}" varStatus="status">
            <tr>
                <td>${item.seq}</td>
                <td><a href="getBoard.do?seq=${item.seq}">${item.title }
                    </a></td>
                <td>${item.writer}</td>
            </tr>
        </c:forEach>
    </c:otherwise>
</c:choose>
```

  

**code example**

```jsp
<c:forEach var="item" items="${list}" begin=0 end=5 step=1 varStatus="status">
    번호 : ${status.count}
    이름 : ${item.name}
    나이 : ${item.age}
    주소 : ${item.addr}
</c:forEach>
```

속성들
- var : 사용할 변수명
- items : Collection 객체(List, ArrayList 등등)
- begin : 시작 index, 정의되지 않을 경우는 디폴트로 0
- end : 종료 index, 정의되지 않을 경우는 items 크기 -1
- step : 반복할때 이동할 index 갯수
- varStatus : 반복상태를 알수있는 변수


varStatus 

- index : (int), items에서 반복될때의 index번호. 0부터 시작
- count : (int), 몇번째 반복인지 나타낸다. 1부터 시작
- first : (boolean), 첫번째 반복인지 나타냄
- last : (boolean), 마지막 반복인지 나타냄


**일반적으로 아무 속성 없이 사용할때의 예**

```jsp
<c:forEach var="item" items="${list}">
    이름 : ${item.name}
    나이 : ${item.age}
    주소 : ${item.addr}
</c:forEach>
```

  

# 토큰을 이용한 반복문

문자열을 특정기호(delimeter)로 잘라내서 반복하는 태그다.

Java에서 StringTokenizer를 이용해 토큰을 분리하여 while문을 사용하는 것과 유사한 기능이다.

```jsp
<c:forTokens var="item" items="서울,대전,대구,부산,울산" delims=",">
    지역 : ${item}
</c:forTokens>
```

items에는 문자열을 넣고, delims 에는 분리할 기준 문자열을 넣으면 된다. 각 토큰별로 분리된 문자열은 var로 지정된 변수로 표출이 가능하다.



