// Permitir solo una selección por pregunta
document.querySelectorAll('.question').forEach(group => {
  const options = group.querySelectorAll('.option');
  options.forEach(opt => {
    opt.addEventListener('click', () => {
      options.forEach(o => o.classList.remove('selected'));
      opt.classList.add('selected');
    });
  });
});

// Manejo del formulario
document.getElementById('nanaiForm').addEventListener('submit', function(e) {
  e.preventDefault();

  const selected = document.querySelectorAll('.option.selected');
  if (selected.length < 3) {
    alert("Por favor responde todas las preguntas.");
    return;
  }

  const mood = document.querySelector('#q1 .selected').dataset.value;
  const area = document.querySelector('#q2 .selected').dataset.value;
  const time = parseInt(document.querySelector('#q3 .selected').dataset.value);

  let message = "Te recomendamos: ";

  if (mood === "triste" || area === "estado de ánimo") {
    message += "el Kit de Ánimo Positivo 😊";
  } else if (mood === "ansioso" || area === "estrés") {
    message += "el Kit de Relajación 🧘‍♀️";
  } else if (area === "energía" && time >= 30) {
    message += "el Kit Energizante 🔋";
  } else {
    message += "el Kit de Bienestar Diario ✨";
  }

  document.getElementById('result').innerText = message;
});
