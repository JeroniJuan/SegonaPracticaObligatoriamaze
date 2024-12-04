function generateRandomName() {
    const adjectives = ['Rapida', 'Valenta', 'Misteriosa', 'Perillosa', 'Inquietant', 'Obscura', 'Lúgubre', 'Enginyosa', 'Desafiant', 'Intrigant'];
    const nouns = ['Partida', 'Instancia', 'Cursa', 'Aventura', 'Exploració', 'Missió', 'Travessia', 'Enigma', 'Relleu', 'Odissea'];
    const randomAdjective = adjectives[Math.floor(Math.random() * adjectives.length)];
    const randomNoun = nouns[Math.floor(Math.random() * nouns.length)];
    return randomAdjective + ' ' + randomNoun + ' #' + Math.floor(Math.random() * 1000);
}
document.querySelector('form').addEventListener('submit', function(event) {
    const gameNameField = document.getElementById('gameName');
    if (gameNameField.value.trim() === '') {
        gameNameField.value = generateRandomName();
    }
});