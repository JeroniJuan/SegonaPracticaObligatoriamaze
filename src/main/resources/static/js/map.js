const canvas = document.getElementById('roomCanvas');
const ctx = canvas.getContext('2d');
const thickness = 8;
const margin = 50;
const roomSize = canvas.width - margin * 2;

function drawWallNorthNormal() {
    drawWallNormal(margin, margin, margin + roomSize, margin);
}

function drawWallSouthNormal() {
    drawWallNormal(margin, margin + roomSize, margin + roomSize, margin + roomSize);
}

function drawWallEastNormal() {
    drawWallNormal(margin + roomSize, margin, margin + roomSize, margin + roomSize);
}

function drawWallWestNormal() {
    drawWallNormal(margin, margin, margin, margin + roomSize);
}

function drawWallNorthOpen() {
    drawWallWithDoor(margin, margin, margin + roomSize, margin);
}

function drawWallSouthOpen() {
    drawWallWithDoor(margin, margin + roomSize, margin + roomSize, margin + roomSize);
}

function drawWallEastOpen() {
    drawWallWithDoor(margin + roomSize, margin, margin + roomSize, margin + roomSize);
}

function drawWallWestOpen() {
    drawWallWithDoor(margin, margin, margin, margin + roomSize);
}

function drawWallNorthClosed() {
    drawWallWithClosedDoor(margin, margin, margin + roomSize, margin);
}

function drawWallSouthClosed() {
    drawWallWithClosedDoor(margin, margin + roomSize, margin + roomSize, margin + roomSize);
}

function drawWallEastClosed() {
    drawWallWithClosedDoor(margin + roomSize, margin, margin + roomSize, margin + roomSize);
}

function drawWallWestClosed() {
    drawWallWithClosedDoor(margin, margin, margin, margin + roomSize);
}

function drawWallNormal(x1, y1, x2, y2) {
    ctx.lineWidth = thickness;
    ctx.strokeStyle = "black";
    ctx.beginPath();
    ctx.moveTo(x1, y1);
    ctx.lineTo(x2, y2);
    ctx.stroke();
}

function drawWallWithDoor(x1, y1, x2, y2, doorSize = 50) {
    const totalLength = Math.sqrt((x2 - x1) ** 2 + (y2 - y1) ** 2);
    const startLength = (totalLength - doorSize) / 2;

    const part1X = x1 + ((x2 - x1) * startLength) / totalLength;
    const part1Y = y1 + ((y2 - y1) * startLength) / totalLength;

    const part2X = x2 - ((x2 - x1) * startLength) / totalLength;
    const part2Y = y2 - ((y2 - y1) * startLength) / totalLength;

    ctx.lineWidth = thickness;
    ctx.strokeStyle = "black";

    ctx.beginPath();
    ctx.moveTo(x1, y1);
    ctx.lineTo(part1X, part1Y);
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(part2X, part2Y);
    ctx.lineTo(x2, y2);
    ctx.stroke();
}

function drawWallWithClosedDoor(x1, y1, x2, y2, doorSize = 50) {
    const totalLength = Math.sqrt((x2 - x1) ** 2 + (y2 - y1) ** 2);
    const startLength = (totalLength - doorSize) / 2;

    const part1X = x1 + ((x2 - x1) * startLength) / totalLength;
    const part1Y = y1 + ((y2 - y1) * startLength) / totalLength;

    const part2X = x2 - ((x2 - x1) * startLength) / totalLength;
    const part2Y = y2 - ((y2 - y1) * startLength) / totalLength;

    ctx.lineWidth = thickness;

    ctx.strokeStyle = "black";
    ctx.beginPath();
    ctx.moveTo(x1, y1);
    ctx.lineTo(part1X, part1Y);
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(part2X, part2Y);
    ctx.lineTo(x2, y2);
    ctx.stroke();

    ctx.strokeStyle = "brown";
    ctx.beginPath();
    ctx.moveTo(part1X, part1Y);
    ctx.lineTo(part2X, part2Y);
    ctx.stroke();
}

function drawRoom() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);

    const north = getDoorInfo('north');
    const south = getDoorInfo('south');
    const east = getDoorInfo('east');
    const west = getDoorInfo('west');

    drawWallBasedOnDoor(north, drawWallNorthNormal, drawWallNorthOpen, drawWallNorthClosed);
    drawWallBasedOnDoor(south, drawWallSouthNormal, drawWallSouthOpen, drawWallSouthClosed);
    drawWallBasedOnDoor(east, drawWallEastNormal, drawWallEastOpen, drawWallEastClosed);
    drawWallBasedOnDoor(west, drawWallWestNormal, drawWallWestOpen, drawWallWestClosed);
}

function getDoorInfo(direction) {
    const doorElement = document.getElementById(direction);
    if (!doorElement || !doorElement.value) {
        return null;
    }

    return {
        id: parseInt(doorElement.value, 10),
        open: doorElement.dataset.open === 'true'
    };
}

function drawWallBasedOnDoor(door, drawNormal, drawOpen, drawClosed) {
    if (!door) {
        drawNormal();
    } else if (door.open) {
        drawOpen();
    } else {
        drawClosed();
    }
}

drawRoom();