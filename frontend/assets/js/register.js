console.log("Script cargado correctamente");

// GET ejemplo: listar todos los clientes
fetch("http://localhost:8080/api/clientes")
  .then(response => response.json())
  .then(data => {
    console.log("Clientes:", data);
  })
  .catch(error => console.error("Error:", error));

// FORMULARIO DINÁMICO
document.getElementById("form-registro").addEventListener("submit", function (e) {
  e.preventDefault();

  const nombre = document.getElementById("nombre").value;
  const email = document.getElementById("email").value;
  const contrasena = document.getElementById("contrasena").value;
  const confirmar = document.getElementById("confirmar")?.value || contrasena; // por si no existe el campo
  const direccion = document.getElementById("direccion")?.value || "";
  const telefono = document.getElementById("telefono")?.value || null;
  const edad = document.getElementById("edad")?.value || null;
  const genero = document.getElementById("genero")?.value || null;
  const tipo_usuario = document.getElementById("tipo_usuario")?.value || "cliente";

  if (contrasena !== confirmar) {
    alert("❌ Las contraseñas no coinciden");
    return;
  }

  const nuevoClienteForm = {
    nombre,
    email,
    contrasena,
    direccion,
    telefono,
    edad: edad ? parseInt(edad) : null,
    genero,
    tipo_usuario,
    activo: true
  };

  fetch("http://localhost:8080/api/clientes", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(nuevoClienteForm)
  })
    .then((res) => {
      if (!res.ok) throw new Error("Error al crear usuario");
      return res.json();
    })
    .then((data) => {
      alert("✅ Registro exitoso");
      console.log("Cliente creado:", data);
      document.getElementById("form-registro").reset();
    })
    .catch((err) => {
      console.error("Error:", err);
      alert("❌ Ocurrió un error al registrar. Intenta más tarde.");
    });
});
