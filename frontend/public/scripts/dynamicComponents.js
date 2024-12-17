

const containerOpenPopUpMenu = document.getElementById("containerOppenPopUpMenu");
const svgArrow = containerOpenPopUpMenu?.querySelector("svg");
const svgPath = svgArrow?.querySelector("path");
const backgroundPopUpMenu = document.getElementById("backgorundPopUpMenu");
const popupMenu = document.getElementById("popupMenu");
const body = document.getElementById("body");


function setupPopUpMenu() {
    // Adiciona evento para abrir/fechar popup ao clicar no botão principal.
    containerOpenPopUpMenu?.addEventListener("click", togglePopUp);

    // Adiciona evento para fechar popup ao clicar no fundo.
    backgroundPopUpMenu?.addEventListener("click", togglePopUp);
}

/**
 * Gerencia a exibição do popup e os estilos relacionados.
 */
function togglePopUp() {
    const isOpen = popupMenu.style.opacity === "1";

    // Atualiza transformações e cores do ícone.
    svgArrow.style.transform = isOpen ? "rotate(0deg)" : "rotate(90deg)";
    svgPath.setAttribute("fill", isOpen ? "#ccc" : "orange");

    // Controla visibilidade e estilos do menu popup.
    popupMenu.style.opacity = isOpen ? "0" : "1";
    popupMenu.style.height = isOpen ? "0" : "fit-content";
    popupMenu.style.padding = isOpen ? "0" : "32px 0";

    // Gerencia o scroll do corpo da página.
    body.style.overflowY = isOpen ? "scroll" : "hidden";

    // Controla visibilidade e camada do fundo do popup.
    backgroundPopUpMenu.style.opacity = isOpen ? "0" : "1";
    backgroundPopUpMenu.style.zIndex = isOpen ? "-999" : "999";
}



/**
 * Renderiza os cards de animes na página.
 * @param {Array} animes - Lista de animes.
 * @param {Function} cardTemplate - Função para gerar o conteúdo HTML do card.
 * @param {String} container 
 */
function renderAnimes(animes, cardTemplate, container) {
    const cards = document.getElementById(container);
    animes.forEach(anime => {
        const card = document.createElement('div');
        card.className = 'card';
        card.innerHTML = cardTemplate(anime); // Usa o template passado como parâmetro
        cards.appendChild(card);

        if (isAdm) {
            card.addEventListener('click', () => {
                window.location.href = `../editProduct/editProduct.html?idAnime=${encodeURIComponent(anime.id)}`;
            });

        } else {
            card.addEventListener('click', () => {
                window.location.href = `../productDetails/productDetails.html?idAnime=${encodeURIComponent(anime.id)}`;
            });
        }

    });
}

/**
 * Função para configurar a interface do administrador.
 */

function setupAdminInterface() {
    
    isAdm = payload.role === 'ADMIN';

    document.querySelectorAll('.adminButton').forEach(button => {

        button.style.display = isAdm ? 'flex' : "none";

        button.addEventListener('click', () => {

        });
    });
}

// const token = sessionStorage.getItem('token');
// const payload = decodeToken(token);

document.querySelectorAll('.profileImage').forEach(img => {
    img.src = payload.image;
});