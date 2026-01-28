document.getElementById('cadastroForm').addEventListener('submit', async (event) => {
    event.preventDefault(); // Impede a página de recarregar

    // Captura os valores dos campos
    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    const userData = {
        name: name,
        email: email,
        password: password
    };

    try {
        const response = await fetch('http://localhost:8080/users', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData)
        });

        if (response.ok) {
            alert("Usuário cadastrado com sucesso!");
            window.location.href = "index.html"; // Redireciona para o login
        } else {
            const errorData = await response.json();
            alert("Erro ao cadastrar: " + (errorData.message || "Verifique os dados"));
        }
    } catch (error) {
        console.error("Erro na requisição:", error);
        alert("O servidor está desligado ou houve um erro de rede.");
    }
});