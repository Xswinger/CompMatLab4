let table = document.getElementById("values-table").getElementsByTagName('tbody')[0]
let addButton = document.getElementById("addButton")
let deleteButton = document.getElementById("deleteButton")
let tableDataX = []
let tableDataY = []

$('#file_input').change(function() {
    let selectedFile = this.files[0]

    document.getElementById("name-file").textContent = selectedFile.name

    const reader = new FileReader()
    reader.readAsText(selectedFile)

    let valid = true;

    reader.onload = function (event) {
        let csvData = event.target.result
        let rowData = csvData.split('\n')

        for (let row = 0; row < rowData.length; row++) {

            let pairData = rowData[row].split(',')

            if (pairData[0].trim() === "" || isNaN(pairData[0])) {
                valid = false
                break
            } else {
                tableDataX[row] = pairData[0]
            }

            if (pairData[1].trim() === "" || isNaN(pairData[1])) {
                valid = false
                break
            } else {
                tableDataY[row] = pairData[1]
            }
        }

        let errorMessage = document.getElementById("error-file")

        if (valid) {
            if (tableDataX.length !== new Set(tableDataX).size) {
                errorMessage.innerText = "Невалидный файл"
            } else {
                errorMessage.innerText = ""
                sendData()
            }
        } else {
            errorMessage.innerText = "Невалидный файл"
        }
    }
})

function clearTable() {
    for (let i = 0; i < table.rows.length; i++) {

        let firstCell = table.rows.item(i).cells[0].children.item(0)
        let secondCell = table.rows.item(i).cells[1].children.item(0)

        firstCell.value = ""
        firstCell.className = "input"

        secondCell.value = ""
        secondCell.className = "input"
    }
    sendButton.disabled = true
}

function addRow() {
    if (table.rows.length <= 11) {

        let row = table.insertRow(-1)
        let xCell = row.insertCell(0)
        let yCell = row.insertCell(1)

        let firstButton = document.createElement("input")
        firstButton.className = "input"
        firstButton.type = "text"

        let secondButton = document.createElement("input")
        secondButton.className = "input"
        secondButton.type = "text"

        xCell.appendChild(firstButton)
        yCell.appendChild(secondButton)

        if (table.rows.length === 12) {
            addButton.disabled = true
        }

        if (table.rows.length > 8) {
            deleteButton.disabled = false
        }

    } else {
        addButton.disabled = true
    }
}

function deleteRow() {
    if (table.rows.length < 9) {

        deleteButton.disabled = true

    } else {
        table.deleteRow(-1)

        if (table.rows.length === 8) {
            deleteButton.disabled = true
        }

        if (table.rows.length < 12) {
            addButton.disabled = false
        }
    }
}