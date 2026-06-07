# Llibres

Aplicació web per gestionar la teva llista de llibres. Permet afegir llibres, canviar-ne l'estat (llegit, llegint, pendent) i buscar títols nous mitjançant l'API d'Open Library.

## Tecnologies

- **Backend**: Java 21 + Spring Boot 3.2.5
- **Base de dades**: H2 (fitxer local, persistent entre reinicis)
- **API externa**: [Open Library](https://openlibrary.org)
- **Frontend**: Thymeleaf + HTML/CSS
- **Desktop**: JavaFX WebView

## Execució

### Aplicació d'escriptori (recomanat)

Genera l'app nativa amb jpackage (requereix JDK 21):

```bash
cd backend
mvn package -DskipTests

jpackage --type app-image --name BookSaver --input target --main-jar llibres-1.0.0.jar --runtime-image "C:\Program Files\Java\jdk-21.0.10" --dest ..
```

Obre `BookSaver/BookSaver.exe`. No cal tenir Java instal·lat per executar-la.

### Des del codi font

```bash
cd backend
mvn spring-boot:run
```

L'aplicació arranca a `http://localhost:8080`.

## Base de dades

Les dades es guarden automàticament a `%USERPROFILE%\bookSaver\data\llibresdb`.

## API REST

| Mètode | Endpoint | Descripció |
|--------|----------|------------|
| GET | `/api/books` | Llista tots els llibres |
| GET | `/api/books?status=LLEGIT` | Filtra per estat |
| GET | `/api/books/{id}` | Obté un llibre per ID |
| POST | `/api/books` | Afegeix un llibre |
| PUT | `/api/books/{id}` | Actualitza un llibre |
| DELETE | `/api/books/{id}` | Elimina un llibre |
| GET | `/api/open-library/search?q={query}` | Cerca llibres a Open Library |

Els estats possibles són: `LLEGIT`, `LLEGINT`, `PENDENT`.

## Inspecció de la base de dades

Ves a `http://localhost:8080/h2-console` amb URL `jdbc:h2:file:${user.home}/bookSaver/data/llibresdb`.
