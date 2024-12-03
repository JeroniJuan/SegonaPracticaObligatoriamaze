document.addEventListener("DOMContentLoaded", () => {
    const topMovementsSection = document.querySelector(".topMovements");
    const topTimeSection = document.querySelector(".topTime");
    const myGamesSection = document.querySelector(".myGames");

    const menysMovimentsButton = document.getElementById("topMovements");
    const menysTempsButton = document.getElementById("topTime");
    const mevesPartidesButton = document.getElementById("myGames");

    const sect = [topMovementsSection, topTimeSection, myGamesSection];

    const hideAllSections = () => {
        console.log("Hiding all");
        sect.forEach(sect => sect.style.display = "none");
    };

    menysMovimentsButton.addEventListener("click", () => {
        hideAllSections();
        topMovementsSection.style.display = "block";
    });

    menysTempsButton.addEventListener("click", () => {
        hideAllSections();
        topTimeSection.style.display = "block";
    });

    mevesPartidesButton.addEventListener("click", () => {
        hideAllSections();
        myGamesSection.style.display = "block";
    });

});
