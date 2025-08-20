    const NanaiKitMain = () => {
    return (
        <main>
        <section className="hero">
            <div className="hero-text">
            <h1>Nanai Kit es tu espacio seguro</h1>
            <p>
                Descubre la magia del autocuidado. <br />
                Explora nuestros kits y productos para una vida más relajada y consciente.
            </p>
            <button className="btn-primary">Hacer Test</button>
            </div>
            <div className="hero-image">
            <img src="assets/images/logo.png" alt="Ilustración de kit Nanai" />
            </div>
        </section>
        
        <section className="productos" id="kits">
            <h2>Nuestros Productos Destacados</h2>
            <div className="card-grid">
            <article className="card">
                <img 
                src="assets/images/kitGratitudHome.png" 
                alt="Kit Gratitud" 
                className="kit-destacado1"
                />
                <h3>Kit Gratitud</h3>
                <p>
                Fomenta el agradecimiento, el bienestar y la atención plena que conectan 
                con lo valioso de cada día.
                </p>
            </article>
            <article className="card">
                <img 
                src="assets/images/nanaiKitHome.png" 
                alt="Kit SOS Ansiedad" 
                className="kit-destacado1"
                />
                <h3>Kit SOS Ansiedad</h3>
                <p>
                Primeros auxilios emocionales para episodios intensos. Incluye herramientas 
                de contención.
                </p>
            </article>
            <article className="card">
                <img 
                src="assets/images/kitRutinaHome.png" 
                alt="Kit Rutina" 
                className="kit-destacado1"
                />
                <h3>Kit Rutina</h3>
                <p>
                Recupera hábitos diarios con herramientas que promueven la organización 
                y el autocuidado.
                </p>
            </article>
            </div>
        </section>
        
        <section className="blog" id="blog">
            <h2>Blog</h2>
            <article className="blog-post">
            <img 
                src="assets/images/campoLavandaHome.png" 
                alt="Campos de lavanda" 
                className="kit-img"
            />
            <div>
                <h3>Beneficios de la Lavanda</h3>
                <p>
                Descubre los múltiples beneficios de la lavanda para la salud y el bienestar.
                </p>
                <button className="btn-outline">Leer más</button>
            </div>
            </article>
        </section>
        
        <section className="kit-mes">
            <h2>Nuestro Kit del mes</h2>
            <article className="kit-destacado">
            <img 
                src="assets/images/kitDestacadoHome.png" 
                alt="Kit Calma" 
                className="kit-img"
            />
            <div>
                <h3>Kit Calma</h3>
                <p>
                Un kit completo para crear un ambiente de relajación en casa. Regula la 
                ansiedad leve, tensiones y estados de alerta.
                </p>
                <button className="btn-outline">Ver kit</button>
            </div>
            </article>
        </section>
        
        <section className="colaborar">
            <h2>Colaborar</h2>
            <article className="colabora">
            <img 
                src="assets/images/lavandaHome.png" 
                alt="Lavanda sobre fondo morado" 
                className="kit-img"
            />
            <div>
                <h3>Colabora con Nanai Kit</h3>
                <p>
                Si eres emprendedor en Chile y crees que tus productos calzan con 
                nuestros kits, te invitamos a colaborar.
                </p>
                <button className="btn-outline">Colaborar</button>
            </div>
            </article>
        </section>
        </main>
    );
    };

    export default NanaiKitMain;