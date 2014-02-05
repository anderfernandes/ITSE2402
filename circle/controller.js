function calculate() {
    var r = parseFloat(document.getElementById('radius').value);
    if (isNaN(r)) {
        document.getElementById('data').innerHTML =
            "Please enter numbers only";
        return;
    }
    if (r <= 0) {
        document.getElementById('data').innerHTML =
            "Negative numbers don't make sense";
        return;
    }
    var myCircle = new Circle(r);
    document.getElementById('data').innerHTML = myCircle.getData();
}