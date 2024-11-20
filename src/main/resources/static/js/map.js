const canvas = document.getElementById('roomCanvas');
        const ctx = canvas.getContext('2d');
        const thickness = 8;
        const margin = 50;
        const roomSize = canvas.width - margin * 2;

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

            // Pared superior (normal)
            drawWallNormal(margin, margin, margin + roomSize, margin);

            // Pared derecha (con puerta abierta)
            drawWallWithDoor(margin + roomSize, margin, margin + roomSize, margin + roomSize);

            // Pared inferior (con puerta cerrada)
            drawWallWithClosedDoor(margin, margin + roomSize, margin + roomSize, margin + roomSize);

            // Pared izquierda (normal)
            drawWallNormal(margin, margin, margin, margin + roomSize);
        }

        drawRoom();