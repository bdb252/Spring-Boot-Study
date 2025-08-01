<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*, java.text.*" %>
<%
    // 현재 날짜 가져오기
    Calendar currentDate = Calendar.getInstance();
    if (request.getParameter("date") != null) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date parsed = df.parse(request.getParameter("date"));
            currentDate.setTime(parsed);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    Calendar monthStart = (Calendar) currentDate.clone();
    monthStart.set(Calendar.DAY_OF_MONTH, 1);

    Calendar monthEnd = (Calendar) monthStart.clone();
    monthEnd.set(Calendar.DAY_OF_MONTH, monthStart.getActualMaximum(Calendar.DAY_OF_MONTH));

    Calendar startDate = (Calendar) monthStart.clone();
    startDate.set(Calendar.DAY_OF_WEEK, startDate.getFirstDayOfWeek());

    Calendar endDate = (Calendar) monthEnd.clone();
    endDate.set(Calendar.DAY_OF_WEEK, endDate.getFirstDayOfWeek());
    endDate.add(Calendar.WEEK_OF_MONTH, 1);

    // posts는 request에 저장된 List<Map<String, String>> 형태라고 가정
    List<Map<String, String>> posts = (List<Map<String, String>>) request.getAttribute("posts");
    if (posts == null) posts = new ArrayList<>();

    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>캘린더</title>
    <style>
        .calendar-container {
            display: flex;
            flex-direction: column;
        }
        .calendar-header {
            display: grid;
            grid-template-columns: repeat(7, 1fr);
            font-weight: bold;
            padding: 10px;
            background: #eee;
        }
        .calendar-row {
            display: grid;
            grid-template-columns: repeat(7, 1fr);
        }
        .calendar-cell {
            border: 1px solid #ccc;
            padding: 5px;
            height: 100px;
            position: relative;
        }
        .inactive {
            background: #f9f9f9;
            color: #aaa;
        }
        .today {
            background: #ffeeba;
        }
        .thumbnail {
            width: 100%;
            height: auto;
            max-height: 60px;
            margin-top: 5px;
        }
    </style>
</head>
<body>
<div class="calendar-container">
    <div class="calendar-header">
        <div>일</div>
        <div>월</div>
        <div>화</div>
        <div>수</div>
        <div>목</div>
        <div>금</div>
        <div>토</div>
    </div>

    <%
        Calendar day = (Calendar) startDate.clone();

        while (!day.after(endDate)) {
    %>
        <div class="calendar-row">
            <%
                for (int i = 0; i < 7; i++) {
                    String formattedDate = fmt.format(day.getTime());

                    // 이미지가 있는 포스트 찾기
                    String imagePath = null;
                    for (Map<String, String> post : posts) {
                        if (formattedDate.equals(post.get("date")) && post.get("image") != null) {
                            imagePath = post.get("image");
                            break;
                        }
                    }

                    boolean isInactive = day.get(Calendar.MONTH) != currentDate.get(Calendar.MONTH);
                    boolean isToday = fmt.format(day.getTime()).equals(fmt.format(new Date()));
            %>
            <div class="calendar-cell <%= isInactive ? "inactive" : "" %> <%= isToday ? "today" : "" %>">
                <div><%= day.get(Calendar.DAY_OF_MONTH) %></div>
                <% if (imagePath != null) { %>
                    <img src="<%= imagePath %>" alt="썸네일" class="thumbnail" />
                <% } %>
            </div>
            <%
                    day.add(Calendar.DAY_OF_MONTH, 1);
                }
            %>
        </div>
    <%
        }
    %>
</div>
</body>
</html>
