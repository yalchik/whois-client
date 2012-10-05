Запуск только из консоли.
Формат: java -jar [filename] [server] [domain]
Пример: java -jar aipos2.jar whois.nic.ru yandex.ru

Возможные проблемы:
	1. Не находит java
		Есть 2 пути решения
		1.1 Указываем полный путь к java, например C:\"Program Files"\Java\jdk1.7.0_07\bin\java -jar aipos2.jar whois.nic.ru yandex.ru
		1.2 Или добавляем путь C:\Program Files\Java\jdk1.7.0_07\bin в системную переменную PATH