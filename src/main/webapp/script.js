async function findRoute() {
    const pointA = document.getElementById("pointA").value;
    const pointB = document.getElementById("pointB").value;
    const transportType = document.getElementById("transportType").value;
    console.log("Отправка запроса:", `pointA=${pointA}&pointB=${pointB}&transportType=${transportType}`);


    try {
        const response = await fetch("route", {
            method: "POST",
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: `pointA=${encodeURIComponent(pointA)}&pointB=${encodeURIComponent(pointB)}&transportType=${encodeURIComponent(transportType)}`
        });

        if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);

        const result = await response.json();
        document.getElementById("result").innerHTML = JSON.stringify(result, null, 2);
    } catch (error) {
        console.error("Ошибка запроса:", error);
        document.getElementById("result").innerHTML = "Ошибка при получении маршрута.";
    }
}
