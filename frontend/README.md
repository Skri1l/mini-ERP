# mini-erp-frontend

Только frontend. Backend, Docker Compose и PostgreSQL здесь специально отсутствуют.

## Запуск frontend отдельно

```bash
npm install
npm run dev
```

Vite обычно откроет:

```text
http://localhost:5173
```

## Куда frontend отправляет запросы

Файл:

```text
src/api/client.js
```

берёт адрес API из:

```text
VITE_API_BASE_URL
```

Создай `.env` рядом с `package.json`:

```env
VITE_API_BASE_URL=http://localhost:8080/api
```

Потом перезапусти `npm run dev`.

## Что дальше тебе предстоит сделать самому

1. Запустить backend отдельно.
2. Запустить frontend отдельно.
3. Убедиться, что frontend пытается обратиться к backend.
4. Разобраться с CORS, потому что `5173` и `8080` — разные origins.
5. Потом завернуть frontend и backend в отдельные Docker containers.
6. Потом связать их через Docker Compose.

Это сделано специально, чтобы ты сам прошёл интеграцию и понял каждый слой.
