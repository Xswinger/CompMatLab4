let sendButton = document.getElementById("sendButton")
let resultTable = document.getElementById("result-table").getElementsByTagName('tbody')[0]
let XYTable = document.getElementById("XY-table").getElementsByTagName('tbody')[0]
let coefficients = document.getElementById("coefficients").getElementsByTagName('tbody')[0]
let correlation = document.getElementById("correlation")
let best_function = document.getElementById("best-function")
let functionsName = ["Линейная функция", "Степенная функция", "Полиномиальная второй степени",
    "Полиномиальная третьей степени", "Логарифмическая функция", "Экспоненциальная функция"]

table.onchange = function () {
    let isFilled = true;

    for (let i = 0; i < table.rows.length; i++) {

        let firstCell = table.rows.item(i).cells[0].children.item(0)
        let secondCell = table.rows.item(i).cells[1].children.item(0)

        let firstValue = firstCell.value.replace(',','.')
        let secondValue = secondCell.value.replace(',','.')

        if (firstValue.trim() === "" || isNaN(firstValue)) {
            firstCell.className = "input is-danger"
            isFilled = false
        } else {
            tableDataX[i] = firstCell.value
            firstCell.className = "input"
        }
        if (secondValue.trim() === "" || isNaN(secondValue)) {
            secondCell.className = "input is-danger"
            isFilled = false
        } else {
            tableDataY[i] = secondCell.value
            secondCell.className = "input"
        }
    }

    if (tableDataX.length !== new Set(tableDataX).size) {
        isFilled = false
    }

    sendButton.disabled = !isFilled;

}

function sendData() {

    // for (let row = 0; row < table.rows.length; row++) {
    //     tableDataX[row] = table.rows.item(row).cells[0].children.item(0).value
    //     tableDataY[row] = table.rows.item(row).cells[1].children.item(0).value
    // }

    console.log(tableDataX)
    console.log(tableDataY)
    $.ajax({
        type: "POST",
        url: "./handler",
        data: {
            xData: tableDataX,
            yData: tableDataY
        },
        success: function (response) {
            let gsonData = JSON.parse(response);

            console.log(gsonData)

            drawGraphic()

            OutputData(gsonData)
        }
    })
}

function OutputData(gsonData) {
    resultTable.innerHTML = ""
    XYTable.innerHTML = ""
    coefficients.innerHTML = ""


    correlation.innerHTML = `<span>${gsonData.correlationCoefficient}</span>`

    let minimal_deviation = 1000;
    let best_func = ""

    for (let i = 0; i < gsonData.response.length; i++) {

        addGraphic(gsonData.response[i].approximate, functionsName[i])

        let row = resultTable.insertRow(-1)
        let naming = row.insertCell(0)
        let fiCell = row.insertCell(1)
        let epsilonCell = row.insertCell(2)
        let standCell = row.insertCell(3)

        let namingValue = document.createElement("span")
        namingValue.innerText = functionsName[i]

        let fiValue = document.createElement("span")
        fiValue.innerHTML = `${gsonData.response[i].approximate}`

        let epsilonsValue = document.createElement("span")
        epsilonsValue.innerHTML = `${gsonData.response[i].epsilons}`

        let standValue = document.createElement("span")
        standValue.innerHTML = `${gsonData.response[i].standardDeviation}`

        if (gsonData.response[i].standardDeviation < minimal_deviation) {
            minimal_deviation = gsonData.response[i].standardDeviation
            best_func = functionsName[i]
        }

        naming.appendChild(namingValue)
        fiCell.appendChild(fiValue)
        epsilonCell.appendChild(epsilonsValue)
        standCell.appendChild(standValue)

        let rowC = coefficients.insertRow(-1)

        let namingC = rowC.insertCell(0)

        let namingValueC = document.createElement("span")
        namingValueC.innerText = functionsName[i]
        namingC.appendChild(namingValueC)

        for (let j = 0; j < gsonData.response[i].coefficients.length; j++) {
            let cellC = rowC.insertCell(j+1)

            let coefficientC = document.createElement("span")
            coefficientC.innerHTML = `${gsonData.response[i].coefficients[j]}`

            cellC.appendChild(coefficientC)
        }
    }

    best_function.innerText = best_func

    for (let i = 0; i < tableDataX.length; i++) {
        let XYrow = XYTable.insertRow(-1)
        let XYx = XYrow.insertCell(0)
        let XYy = XYrow.insertCell(1)

        let xValue = document.createElement("span")
        xValue.innerText = tableDataX[i]

        let yValue = document.createElement("span")
        yValue.innerText = tableDataY[i]

        XYx.appendChild(xValue)
        XYy.appendChild(yValue)

    }
}