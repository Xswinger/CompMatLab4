<%@ page contentType="text/html;charset=utf-8" %>

<html style="background: linear-gradient(351deg, rgba(2,0,36,1) 0%, rgba(0,156,142,1) 0%, rgba(0,212,255,1) 100%);">
<head>
    <meta charset="UTF-8">
    <title>Лабораторная работа №4</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
    <link rel="icon" type="image/x-icon" href="style/icon.svg">
</head>
<body>
    <h1 class="title is-1 has-text-centered">Аппроксимация функции методом наименьших квадратов</h1>
    <div class="columns">
        <div class="column has-text-centered m-2">
            <span class="title is-3">Ввод исходных данных</span>
            <div class="level mt-5">
                <h4 class="level-item">Ввод из файла:</h4>
                <div class="file level-item">
                    <label class="file-label">
                        <input id="file_input" class="file-input" type="file" name="resume" accept=".txt">
                        <span class="file-cta">
                            <span class="file-label">
                                Выберите файл…
                            </span>
                        </span>
                        <span id="name-file" class="file-name">
                            Файл не выбран
                        </span>
                    </label>
                </div>
            </div>
            <div class="columns">
                <div class="column">
                    <table id="values-table" class="table is-fullwidth is-bordered">
                        <thead>
                        <tr>
                            <th class="has-text-centered" title="X">X</th>
                            <th class="has-text-centered" title="Y">Y</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                <input class="input" type="text">
                            </td>
                            <td>
                                <input class="input" type="text">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input class="input" type="text">
                            </td>
                            <td>
                                <input class="input" type="text">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input class="input" type="text">
                            </td>
                            <td>
                                <input class="input" type="text">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input class="input" type="text">
                            </td>
                            <td>
                                <input class="input" type="text">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input class="input" type="text">
                            </td>
                            <td>
                                <input class="input" type="text">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input class="input" type="text">
                            </td>
                            <td>
                                <input class="input" type="text">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input class="input" type="text">
                            </td>
                            <td>
                                <input class="input" type="text">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input class="input" type="text">
                            </td>
                            <td>
                                <input class="input" type="text">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="column is-narrow pl-0">
                    <div>
                        <button id="addButton" class="button" onclick="addRow()">+</button>
                    </div>
                    <div>
                        <button id="deleteButton" class="button mt-1" onclick="deleteRow()" disabled>-</button>
                    </div>
                </div>
            </div>
            <div>
                <button id="sendButton" class="button is-primary" onclick="sendData()" disabled>Посчитать</button>
                <button id="clearButton" class="button is-warning" onclick="clearTable()">Очистить</button>
            </div>
        </div>
        <div class="column has-text-centered">
            <div class="mb-3">
                <span class="title is-3">График</span>
            </div>
            <div>
                <canvas class="mr-3" id="canvas_background" style="height: 400px"></canvas>
            </div>
            <span class="title is-3" style="color: red" id="error-file"></span>
        </div>
    </div>
    <div class="has-text-centered">
        <div>
            <span class="title is-3">Результат</span>
        </div>
        <table class="table is-fullwidth is-bordered mt-5" id="result-table">
            <thead>
            <tr>
                <th class="has-text-centered" title="naming">Функция</th>
                <th class="has-text-centered" title="fi(x)">Приближенные значения</th>
                <th class="has-text-centered" title="epsilon">Отклонение</th>
                <th class="has-text-centered" title="standard deviation">Среднеквадратичное отклонение</th>
            </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
        <div>
            <span class="title is-3">Наилучшая функция</span>
            <br>
            <span id="best-function" class="title is-3">Пока никакая</span>
        </div>
        <br>
        <span class="title is-3">Исходные данные:</span>
        <table class="table is-fullwidth is-bordered" id="XY-table">
            <thead>
            <tr>
                <th class="has-text-centered" title="x">X</th>
                <th class="has-text-centered" title="y">Y</th>
            </tr>
            </thead>
            <tbody class="has-text-centered">

            </tbody>
        </table>
        <div>
            <div>
                <span class="title is-3">Коэффициенты:</span>
            </div>
            <table class="table is-fullwidth is-bordered mt-5" id="coefficients">
                <thead>
                <tr>
                    <th class="has-text-centered" title="naming">Функция</th>
                    <th class="has-text-centered" title="a_0">Коэффициент №1</th>
                    <th class="has-text-centered" title="b_0">Коэффициент №2</th>
                    <th class="has-text-centered" title="b_0">Коэффициент №3</th>
                    <th class="has-text-centered" title="b_0">Коэффициент №4</th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
        <div>
            <span class="title is-3">Коэффициент корреляции Пирсона:</span>
            <br>
            <span class="title is-3" id="correlation">
                Пока пусто
            </span>
        </div>
    </div>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="js/table.js"></script>
<script src="js/controller.js"></script>
<script src="js/canvas.js"></script>
</body>
</html>
