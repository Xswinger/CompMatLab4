let canvas = document.getElementById("canvas_background")
let chart

$(document).ready(function () {
    chart = new Chart(canvas, {
        type: 'line',
        data: {
            labels: [],
            datasets: [{
                label: 'Начальный график',
                data: [],
                backgroundColor: 'rgb(0,255,0)',
                borderColor: 'rgb(13,61,0)',
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: false
                    }
                }]
            }
        }
    })
})

function drawGraphic() {

    let uniqueXValues = tableDataX;
    let uniqueYValues = tableDataY;

    const data = {
        labels: uniqueXValues,
        datasets: [{
            label: 'Начальный график',
            data: uniqueYValues,
            backgroundColor: 'rgba(0,255,0)',
            borderColor: 'rgba(13,61,0)',
            borderWidth: 1
        }]
    };

    const options = {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero: false,
                    fontSize: 10,
                    max: Math.max(...uniqueYValues),
                    min: Math.min(...uniqueYValues)
                }
            }],
            xAxes: [{
                ticks: {
                    fontSize: 10,
                    max: Math.max(...uniqueXValues),
                    min: Math.min(...uniqueXValues)
                }
            }]
        },
        legend: {
            display: true,
            labels: {
                fontSize: 10,
            }
        }
    }

    chart.data = data;
    chart.options = options;

    chart.update();

}

function addGraphic(approximate, name) {

    chart.data.datasets.push({
        label: name,
        data: approximate,
        backgroundColor: 'rgba(175,0,39,0.8)',
        borderColor: 'rgb(255,237,34)',
        borderWidth: 1
    });

    chart.update();

}