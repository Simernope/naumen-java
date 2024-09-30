# To-Do List

Консольное приложение для управления задачами.

## Возможности
- Создание задач
- Чтение задач
- Обновление задач
- Удаление задач

## Использование
- Создание: `create <id> "<описание>" "<статус>" "<дедлайн>"`
- Чтение: `read <id>`
- Обновление: `update <id> "<новое описание>" "<новый статус>" "<новый дедлайн>"`
- Удаление: `delete <id>`
- Выход: `exit`

Примеры команд:
- Создание: `create 1 "Написать отчёт" "TODO" "2024-10-01"`
- Чтение: `read 1`
- Обновление: `update 1 "Написать отчёт по проекту" "IN_PROGRESS" "2024-10-05"`
- Удаление: `delete 1`
- Выход: `exit`