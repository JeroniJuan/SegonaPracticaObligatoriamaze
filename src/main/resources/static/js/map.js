const canvas = document.getElementById('roomCanvas');
const roomid = document.getElementById('roomid');
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
    drawWallWithClosedDoor(margin, margin, margin + roomSize, margin, "n");
}

function drawWallSouthClosed() {
    drawWallWithClosedDoor(margin, margin + roomSize, margin + roomSize, margin + roomSize, "s");
}

function drawWallEastClosed() {
    drawWallWithClosedDoor(margin + roomSize, margin, margin + roomSize, margin + roomSize, "e");
}

function drawWallWestClosed() {
    drawWallWithClosedDoor(margin, margin, margin, margin + roomSize, "w");
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

function drawWallWithClosedDoor(x1, y1, x2, y2, dir, doorSize = 50) {
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

    const clickMargin = 10;

    canvas.addEventListener('click', function handleClick(event) {
        const rect = canvas.getBoundingClientRect();
        const mouseX = event.clientX - rect.left;
        const mouseY = event.clientY - rect.top;

        const clickOnDoor =
            mouseX >= Math.min(part1X, part2X) - clickMargin &&
            mouseX <= Math.max(part1X, part2X) + clickMargin &&
            mouseY >= Math.min(part1Y, part2Y) - clickMargin &&
            mouseY <= Math.max(part1Y, part2Y) + clickMargin;

        if (clickOnDoor) {
            const form = document.createElement('form');
            form.method = 'POST';
            form.action = '/open';

            const dirInput = document.createElement('input');
            dirInput.type = 'hidden';
            dirInput.name = 'dir';
            dirInput.value = dir;

            form.appendChild(dirInput);
            document.body.appendChild(form);
            form.submit();

            canvas.removeEventListener('click', handleClick);
        }
    });
}



function drawCoin() {
    const coinRadius = 15;
    const coinX = margin + roomSize / 4;
    const coinY = margin + roomSize / 4;

    ctx.fillStyle = "gold";
    ctx.beginPath();
    ctx.arc(coinX, coinY, coinRadius, 0, 2 * Math.PI);
    ctx.fill();

    canvas.addEventListener('click', function handleClick(event) {
        const rect = canvas.getBoundingClientRect();
        const mouseX = event.clientX - rect.left;
        const mouseY = event.clientY - rect.top;

        if (Math.sqrt((mouseX - coinX) ** 2 + (mouseY - coinY) ** 2) <= coinRadius) {
            console.log("Moneda clickeada");

            const form = document.createElement('form');
            form.method = 'POST';
            form.action = '/getCoin';

            document.body.appendChild(form);
            form.submit();

            canvas.removeEventListener('click', handleClick);
        }
    });
}


function drawKey() {
    const keyWidth = 10;
    const keyHeight = 30;

    const keyX = margin + (3 * roomSize) / 4;
    const keyY = margin + (3 * roomSize) / 4;

    ctx.fillStyle = "silver";
    ctx.beginPath();
    ctx.rect(keyX, keyY, keyWidth, keyHeight);
    ctx.fill();

    canvas.addEventListener('click', function handleClick(event) {
        const rect = canvas.getBoundingClientRect();
        const mouseX = event.clientX - rect.left;
        const mouseY = event.clientY - rect.top;

        if (
            mouseX >= keyX &&
            mouseX <= keyX + keyWidth &&
            mouseY >= keyY &&
            mouseY <= keyY + keyHeight
        ) {
            console.log("Llave clickeada");

            const form = document.createElement('form');
            form.method = 'POST';
            form.action = '/getKey';

            document.body.appendChild(form);
            form.submit();

            canvas.removeEventListener('click', handleClick);
        }
    });
}



function drawRoom() {
        if (roomid && roomid.value == 7) {
            const playerName = prompt("Por favor, introduce tu nombre de jugador:");

            if (playerName) {
                const form = document.createElement('form');
                form.method = 'POST';
                form.action = '/endForm';

                const nameInput = document.createElement('input');
                nameInput.type = 'hidden';
                nameInput.name = 'name';
                nameInput.value = playerName;

                form.appendChild(nameInput);
                document.body.appendChild(form);
                form.submit();
            } else {
                alert("Debes ingresar un nombre para continuar.");
            }
            return;
        }
    const coinElement = document.getElementById('coin');
    const keyElement = document.getElementById('key');
    const coin = coinElement && coinElement.value === 'true';
    const key = keyElement && keyElement.value;

    ctx.clearRect(0, 0, canvas.width, canvas.height);

    const north = getDoorInfo('north');
    const south = getDoorInfo('south');
    const east = getDoorInfo('east');
    const west = getDoorInfo('west');

    drawWallBasedOnDoor(north, drawWallNorthNormal, drawWallNorthOpen, drawWallNorthClosed);
    drawWallBasedOnDoor(south, drawWallSouthNormal, drawWallSouthOpen, drawWallSouthClosed);
    drawWallBasedOnDoor(east, drawWallEastNormal, drawWallEastOpen, drawWallEastClosed);
    drawWallBasedOnDoor(west, drawWallWestNormal, drawWallWestOpen, drawWallWestClosed);

    if (coin) {
        drawCoin();
    }

    if (key && key !== "") {
        drawKey();
    }
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