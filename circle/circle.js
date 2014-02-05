function Circle(r) {
    this.radius = r;
    this.getArea = getA;
    this.getPerimeter = getP;
    this.getData = getD;
}

function getA() {
    return this.radius * this.radius * Math.PI;
}

function getP() {
    return 2 * this.radius * Math.PI;
}

function getD() {
    return 'Area: ' + this.getArea() + '<br />Perimeter: ' +
        this.getPerimeter();
}