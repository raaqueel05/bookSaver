# Llibres

Aplicació web per gestionar la teva llista de llibres. Permet afegir llibres, canviar-ne l'estat (llegit, llegint, pendent) i buscar títols nous mitjançant l'API d'Open Library.

## Tecnologies

- **Backend**: Java 21 + Spring Boot 3.2.5
- **Base de dades**: H2 (fitxer local, persistent entre reinicis)
- **API externa**: [Open Library](https://openlibrary.org)
- **Frontend**: Thymeleaf + HTML/CSS

## Requisits

- Java 21
- Maven 3.x

## Execució

```bash
cd backend
mvn spring-boot:run
```

L'aplicació arranca a `http://localhost:8080`.

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

## Base de dades

La BD H2 es guarda a `data/llibresdb.mv.db`. Per inspeccionar-la, ves a `http://localhost:8080/h2-console` amb URL `jdbc:h2:file:./data/llibresdb`.
