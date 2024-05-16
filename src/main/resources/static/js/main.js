const FACTORIAL_URL = '/load/';
let load = false;

function startLoad() {
    let text = document.getElementById('processing-text');
    text.removeAttribute("hidden");
    let stopButton = document.getElementById('workload-stop');
    stopButton.removeAttribute("hidden");
    let startButton = document.getElementById('workload-start');
    startButton.setAttribute("hidden","");
    load = true;
    const number = document.getElementById('number');
    infiniteReload(FACTORIAL_URL + number.value, 50, 8);
}
function stopLoad() {
    // callWithInterval(() => infiniteReload(FACTORIAL_URL + number.value), 50, 8);
    let text = document.getElementById('processing-text');
    text.setAttribute("hidden","");
    let stopButton = document.getElementById('workload-stop');
    stopButton.setAttribute("hidden","");
    let startButton = document.getElementById('workload-start');
    startButton.removeAttribute("hidden");
    load = false;
}

function setSound(enable) {
    var http = new XMLHttpRequest();
    var url = "/setSound/"+enable;
    http.open("POST", url, true);
    http.setRequestHeader("Content-type", "application/json; charset=utf-8");
    http.onreadystatechange = function() {
        if(http.readyState == 4 && http.status == 200) {
            document.getElementById('sound-status').innerText = http.responseText;
        } else {
          console.log(`Error: ${http.status}`);
        }
    }
    http.send(null);
}

function infiniteReload(url, sleepMs) {
    if (load === false ) {
        return;
    }

    let xhttp = new XMLHttpRequest();

    xhttp.onload = function() {
        const number = document.getElementById('number');
        setTimeout(() => infiniteReload(FACTORIAL_URL + number.value, sleepMs), sleepMs)
    }
    xhttp.open('GET', url);
    xhttp.send();
}