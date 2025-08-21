// Datos de prueba para blogs sobre salud mental
export const blogPosts = [
  {
    id: 1,
    title: "Cómo manejar la ansiedad en el día a día",
    excerpt: "Descubre técnicas efectivas para reducir la ansiedad y encontrar calma en tu rutina diaria.",
    content: "La ansiedad es una respuesta natural del cuerpo ante situaciones de estrés, pero cuando se vuelve constante puede afectar significativamente nuestra calidad de vida. En este artículo exploraremos técnicas de respiración, mindfulness y estrategias prácticas para manejar la ansiedad de manera efectiva.",
    author: "Dra. María González",
    date: "2024-01-15",
    readTime: "5 min",
    category: "Ansiedad",
    image: "/assets/images/blog-ansiedad.jpg",
    tags: ["ansiedad", "mindfulness", "bienestar", "técnicas"]
  },
  {
    id: 2,
    title: "La importancia del autocuidado en la salud mental",
    excerpt: "Aprende por qué el autocuidado no es un lujo, sino una necesidad fundamental para tu bienestar emocional.",
    content: "El autocuidado va más allá de los tratamientos de spa o las compras. Se trata de crear hábitos y rutinas que nutran tu mente, cuerpo y espíritu. Exploraremos diferentes formas de autocuidado y cómo integrarlas en tu vida diaria.",
    author: "Psic. Carlos Mendoza",
    date: "2024-01-10",
    readTime: "7 min",
    category: "Autocuidado",
    image: "/assets/images/blog-autocuidado.jpg",
    tags: ["autocuidado", "bienestar", "rutinas", "salud mental"]
  },
  {
    id: 3,
    title: "Meditación para principiantes: Tu primer paso hacia la paz interior",
    excerpt: "Una guía completa para comenzar tu práctica de meditación y encontrar momentos de calma en tu día.",
    content: "La meditación es una herramienta poderosa para cultivar la paz interior y reducir el estrés. En esta guía para principiantes, aprenderás técnicas básicas de meditación, cómo crear un espacio de práctica y consejos para mantener la constancia.",
    author: "Maestra Zen Ana López",
    date: "2024-01-05",
    readTime: "6 min",
    category: "Meditación",
    image: "/assets/images/blog-meditacion.jpg",
    tags: ["meditación", "principiantes", "paz interior", "mindfulness"]
  },
  {
    id: 4,
    title: "Cómo construir relaciones saludables y significativas",
    excerpt: "Explora las claves para desarrollar conexiones auténticas que nutran tu bienestar emocional.",
    content: "Las relaciones saludables son fundamentales para nuestro bienestar mental. Aprenderemos sobre comunicación efectiva, establecimiento de límites, y cómo cultivar conexiones que nos apoyen y nos hagan crecer como personas.",
    author: "Dra. Laura Fernández",
    date: "2023-12-28",
    readTime: "8 min",
    category: "Relaciones",
    image: "/assets/images/blog-relaciones.jpg",
    tags: ["relaciones", "comunicación", "límites", "bienestar"]
  },
  {
    id: 5,
    title: "Gestión del estrés: Herramientas prácticas para el mundo moderno",
    excerpt: "Descubre estrategias efectivas para manejar el estrés en un mundo cada vez más acelerado.",
    content: "El estrés es inevitable en la vida moderna, pero podemos aprender a gestionarlo de manera efectiva. Exploraremos técnicas de manejo del tiempo, ejercicios de relajación y estrategias para crear un equilibrio saludable entre trabajo y vida personal.",
    author: "Dr. Roberto Silva",
    date: "2023-12-20",
    readTime: "6 min",
    category: "Estrés",
    image: "/assets/images/blog-estres.jpg",
    tags: ["estrés", "gestión", "equilibrio", "relajación"]
  },
  {
    id: 6,
    title: "El poder de la gratitud en tu bienestar mental",
    excerpt: "Aprende cómo practicar la gratitud puede transformar tu perspectiva y mejorar tu salud mental.",
    content: "La gratitud es más que un sentimiento; es una práctica que puede transformar nuestra manera de ver el mundo. Descubriremos los beneficios científicamente comprobados de la gratitud y cómo incorporar esta práctica en tu vida diaria.",
    author: "Psic. Elena Morales",
    date: "2023-12-15",
    readTime: "5 min",
    category: "Gratitud",
    image: "/assets/images/blog-gratitud.jpg",
    tags: ["gratitud", "bienestar", "perspectiva", "práctica"]
  }
];

// Categorías disponibles
export const blogCategories = [
  "Todos",
  "Ansiedad",
  "Autocuidado",
  "Meditación",
  "Relaciones",
  "Estrés",
  "Gratitud"
];

// Función para obtener posts por categoría
export const getPostsByCategory = (category) => {
  if (category === "Todos") {
    return blogPosts;
  }
  return blogPosts.filter(post => post.category === category);
};

// Función para obtener un post por ID
export const getPostById = (id) => {
  return blogPosts.find(post => post.id === parseInt(id));
};

// Función para obtener posts relacionados
export const getRelatedPosts = (currentPostId, limit = 3) => {
  return blogPosts
    .filter(post => post.id !== parseInt(currentPostId))
    .slice(0, limit);
};