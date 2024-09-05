[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/nATNgroU)
# 🎵 API de Álbuns

Resolva esse CRUD de Album:

## 🌐 Endpoints

### 1. Listar Álbuns
- **Endpoint:** `GET /albuns`
- **Descrição:** Retorna a lista de todos os álbuns cadastrados.
- **Resposta:**
    - 200 OK: Retorna a lista de álbuns.
    - 204 No Content: Nenhum álbum encontrado.

### 2. Criar Álbum
- **Endpoint:** `POST /albuns`
- **Descrição:** Cria um novo álbum.
- **Entrada:**
    - Corpo da requisição: Dados do álbum (JSON).
- **Resposta:**
    - 201 Created: Retorna o álbum criado.

### 3. Buscar Álbum por ID
- **Endpoint:** `GET /albuns/{id}`
- **Descrição:** Busca um álbum específico pelo ID.
- **Parâmetro:**
    - `id`: ID do álbum.
- **Resposta:**
    - 200 OK: Retorna o álbum encontrado.
    - 404 Not Found: Álbum não encontrado.

### 4. Atualizar Álbum
- **Endpoint:** `PUT /albuns/{id}`
- **Descrição:** Atualiza um álbum existente pelo ID.
- **Parâmetro:**
    - `id`: ID do álbum.
- **Entrada:**
    - Corpo da requisição: Dados atualizados do álbum (JSON).
- **Resposta:**
    - 200 OK: Retorna o álbum atualizado.
    - 404 Not Found: Álbum não encontrado.

### 5. Deletar Álbum
- **Endpoint:** `DELETE /albuns/{id}`
- **Descrição:** Deleta um álbum pelo ID.
- **Parâmetro:**
    - `id`: ID do álbum.
- **Resposta:**
    - 204 No Content: Álbum deletado com sucesso.
    - 404 Not Found: Álbum não encontrado.

### 6. Buscar Álbuns por Artista
- **Endpoint:** `GET /albuns/artistas`
- **Descrição:** Busca álbuns por nome do artista.
- **Parâmetro:**
    - `nome`: Nome parcial ou completo do artista.
- **Resposta:**
    - 200 OK: Retorna a lista de álbuns encontrados.
    - 204 No Content: Nenhum álbum encontrado.

### 7. Buscar Álbum Mais Vendido
- **Endpoint:** `GET /albuns/mais-vendido`
- **Descrição:** Retorna o álbum com maior número de vendas.
- **Resposta:**
    - 200 OK: Retorna o álbum mais vendido.
    - 404 Not found: Nenhum álbum encontrado.

### 8. Buscar Álbum Menos Vendido
- **Endpoint:** `GET /albuns/menos-vendido`
- **Descrição:** Retorna o álbum com menor número de vendas.
- **Resposta:**
    - 200 OK: Retorna o álbum menos vendido.
    - 404 Not found: Nenhum álbum encontrado.

### 9. Buscar Álbuns por Período
- **Endpoint:** `GET /albuns/periodo`
- **Descrição:** Busca álbuns lançados dentro de um intervalo de datas.
- **Parâmetro:**
    - `inicio`: Data de início (formato `YYYY-MM-DD`).
    - `fim`: Data de fim (formato `YYYY-MM-DD`).
- **Resposta:**
    - 200 OK: Retorna a lista de álbuns encontrados.
    - 204 No Content: Nenhum álbum encontrado.
    - 400 Bad Request: Período inválido (data de início é maior que a data de fim).

## 🎵 Modelo de Álbum

O modelo de álbum contém as seguintes propriedades:

- **id**: Identificador único do álbum (Integer).
- **nome**: Nome do álbum (String).
- **artista**: Nome do artista (String).
- **dataLancamento**: Data de lançamento do álbum (LocalDate).
- **quantidadeMusicas**: Quantidade de músicas no álbum (Integer).
- **totalVendas**: Total de vendas do álbum (Double).
