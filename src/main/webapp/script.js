async function findRoute() {
    const pointA = document.getElementById("pointA").value;
    const pointB = document.getElementById("pointB").value;
    const transportType = document.getElementById("transportType").value;
    const regex = /^-?\d+(\.\d+)?,-?\d+(\.\d+)?$/;

    if (!regex.test(pointA) || !regex.test(pointB)) {
        document.getElementById("result").innerHTML = "Пожалуйста, введите корректные координаты для точек A и B.";
        return;
    }

    if (!transportType) {
        document.getElementById("result").innerHTML = "Пожалуйста, выберите тип транспорта.";
        return;
    }

    console.log("Отправка запроса:", `pointA=${pointA}&pointB=${pointB}&transportType=${transportType}`);

    try {
        const response = await fetch("route", {
            method: "POST",
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: `pointA=${encodeURIComponent(pointA)}&pointB=${encodeURIComponent(pointB)}&transportType=${encodeURIComponent(transportType)}`
        });

        if (!response.ok) throw new Error(`HTTP error! статус: ${response.status}`);

        const result = await response.json();
        if (result.error) {
            throw new Error(result.error);
        }

        displayRoute(result);
    } catch (error) {
        console.error("Ошибка запроса:", error);
        document.getElementById("result").innerHTML = `Ошибка при получении маршрута: ${error.message}`;
    }
}

function displayRoute(route) {
    const routeDetails = document.getElementById("routeDetails");
    if (route && route.result) {
        let routeHtml = '';

        route.result.route.forEach((segment, index) => {
            routeHtml += `
                <div class="route-segment">
                    <h4>Маршрут ${index + 1}: ${segment.segment}</h4>
                    <p><strong>Время отправления:</strong> ${segment.start_time}</p>
                    <p><strong>Время прибытия:</strong> ${segment.end_time}</p>
                    <p><strong>Остановки:</strong></p>
                    <ul>
                        ${segment.stops.map(stop => `<li>${stop.stop_name}: ${stop.arrival_time}</li>`).join('')}
                    </ul>
                </div>
            `;
        });

        routeDetails.innerHTML = routeHtml;
    } else {
        routeDetails.innerHTML = "Маршрут не найден.";
    }
}
