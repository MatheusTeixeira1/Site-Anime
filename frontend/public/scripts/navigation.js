/**
 * Configura os eventos de navegação do site.
 */
function setupNavigation() {
    document.querySelectorAll('.homeButton').forEach(button => {
        button.addEventListener('click', () => {
            window.location.href = "../products/products.html";
        });
    });

    // Redirecionamento para página de perfil
    document.querySelectorAll('.profileButton').forEach(button => {
        button.addEventListener('click', () => {
            window.location.href = "../profileDetails/profileDetails.html";
        });
    });

    // Redirecionamento para página de favoritos
    document.querySelectorAll('.favoritesButton').forEach(button => {
        button.addEventListener('click', () => {
            alert("Favoritos");
            // window.location.href = "favorites.html";
        });
    });

    // Botão de voltar ao topo
    document.querySelectorAll('.backToTop').forEach(button => {
        button.addEventListener('click', () => {
            window.scrollTo({ top: 0, behavior: 'smooth' });
        });
    });

    // Redirecionamento para página de administrador

    
    

    document.querySelectorAll('.adminButton').forEach(button => {
        button.addEventListener('click', () => {
            window.location.href = '../createProduct/createProduct.html';
        });
    });

    // Botões de logout
    document.querySelectorAll('.logoutButton').forEach(button => {
        button.addEventListener('click', () => {
            sessionStorage.removeItem('token');
            window.location.href = '../login/login.html';
        });
    });

    // Redireciona para a página principal ao clicar no logotipo
    document.getElementById('brandLogoContainer')?.addEventListener('click', () => {
        window.location.href = "#";
    });
}
