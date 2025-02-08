<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="script.js" defer></script>
    <title>Поиск маршрута</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .route-segment {
            background-color: #f9f9f9;
            padding: 15px;
            margin-bottom: 15px;
            border-radius: 8px;
        }
        .route-segment h4 {
            margin: 0;
            color: #333;
        }
        .route-segment p {
            margin: 5px 0;
        }
        #result {
            margin-top: 20px;
        }
    </style>
</head>
<body>
<h2>Поиск маршрута</h2>
<label>Координаты точки A (lat,lng):</label>
<input type="text" id="pointA" placeholder="55.751244,37.618423">
<br>
<label>Координаты точки B (lat,lng):</label>
<input type="text" id="pointB" placeholder="55.760186,37.618704">
<br>
<label>Тип транспорта:</label>
<select id="transportType">
    <option value="bus">Автобус</option>
    <option value="tram">Трамвай</option>
    <option value="metro">Метро</option>
</select>
<br>
<button onclick="findRoute()">Найти маршрут</button>
<div id="result">
    <h3>Результаты поиска маршрута</h3>
    <div id="routeDetails">
        <p>Здесь будет отображен результат маршрута после запроса</p>
    </div>
</div>
</body>
</html>
