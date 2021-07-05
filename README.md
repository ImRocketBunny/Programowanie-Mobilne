# PRM Projekt - 01

Zasady

● Termin przesłania projektu: do 03.05.2021 r.
Termin obrony projektu: 06 lub 10.05.2021 r. podczas zajęć swojej grupy

● W ocenie projektu poza praktyczną i merytoryczną poprawnością będzie brana
również pod uwagę jakość i czytelność napisanego przez Państwa kodu. [1p]

● Projekt powinien zostać zaimplementowany z użyciem natywnych narzędzi Android
SDK (w dowolnym wariancie językowym: kotlin lub java)

● Stwierdzenie popełnienia plagiatu skutkuje otrzymaniem za projekt 0p bez
możliwości poprawy

● Korzystanie z dodatkowych bibliotek i zależności skutkuje obniżeniem punktacji o
połowę podpunktu, który ów biblioteka realizuje. Wyjątkami są biblioteki androidx.*,
oraz com.google.android.*

● istnieje możliwość przesłania (do 12.05.2021 r.) oraz obrony 13 lub 17.05.2021 r. ale
za maksymalnie połowę punktów możliwych do zdobycia.

Manager Finansowy

Utwórz aplikację do zarządzania swoimi wydatkami.
Aplikacja będzie zawierała 3 ekrany:
1. Ekran listy dochodów i wydatków:

● Na ekranie wyświetlana będzie lista poniesionych wydatków oraz uzyskanych
dochodów. Dla każdego wpisu widoczna będzie informacja z nazwą miejsca
związanego z wydatkiem/dochodem (np: Biedronka, Multikino, PJATK), kwotą
wpisu, datą oraz kategorią jakiej dotyczy (np: jedzenie, zdrowie, opłaty, rozrywka
itp.). [1p]

● Ekran powinien zawierać podsumowanie wydatków/dochodów z aktualnego
miesiąca (sumę wpisów) [1p]

● Wybór elementu listy umożliwi jego edycję, a dłuższe przytrzymanie spowoduje
alert z zapytaniem o usunięcie pozycji. Jeśli użytkownik zatwierdzi usunięcie -
wpis znika z listy, a podsumowanie się aktualizuje. [1p]

● Ekran powinien zawierać również przycisk umożliwiający dodanie nowego wpisu
oraz przycisk przekierowujący na zestawienie miesięczne.

● Zapisywanie danych nie należy do zakresu tego zadania. Wprowadzone zmiany
mogą się restartować przy każdym zamknięciu aplikacji.

2. Ekran dodawania/edycji wpisu - uruchamia się w następstwie kliknięcia przycisku
dodającego lub w przypadku edycji istniejącego wpisu na liście.

● Ekran ten umożliwia nadanie/zmianę danych wpisu oraz zawiera przycisk
zapisujący dokonane zmiany/wprowadzone dane. [2p]

3. Ekran zestawienia miesięcznego (z ostatniego miesiąca). [2p]

Treść projektu wzbogacona o poglądowe kryteria oceniania. Należy pamiętać, że finalna
ocena należy do prowadzącego ćwiczenia.

● Ekran zawiera wykres, na którym na osi X zaznaczony jest czas (w dniach), a na
osi Y podsumowanie wpisów.

● Jeśli dla któregoś dnia kwota zejdzie poniżej 0zł, wykres w tym miejscu będzie
miał kolor czerwony. Jeśli powyżej 0zł - zielony.
Aplikacja powinna posiadać zadbaną szatę graficzną i responsywny układ (np: ikony zamiast
tekstu na przyciskach oraz logo na ekranie listy, brak najeżdżających na siebie przycisków,
lista przewijana, itp.) [2p]
Przed obroną projektu należy przygotować zestaw danych przykładowych, w celu
zaprezentowania wszystkich funkcjonalności. (najlepiej dane te umieścić bezpośrednio w
kodzie aplikacji)


# PRM Projekt - 02

Zasady

● Termin przesłania projektu: do 26.05.2021 r.
Termin obrony projektu: 27.05 lub 31.05.2021 r. podczas zajęć swojej grupy

● W ocenie projektu poza praktyczną i merytoryczną poprawnością będzie brana
również pod uwagę jakość i czytelność napisanego przez Państwa kodu.[1p]

● Projekt powinien zostać zaimplementowany z użyciem natywnych narzędzi Android
SDK (w dowolnym wariancie językowym: kotlin lub java)

● Stwierdzenie popełnienia plagiatu skutkuje otrzymaniem za projekt 0p bez
możliwości poprawy

● Korzystanie z dodatkowych bibliotek i zależności skutkuje obniżeniem punktacji o
połowę podpunktu, który ów biblioteka realizuje. Wyjątkami są biblioteki androidx.*,
oraz com.google.android.*

● istnieje możliwość przesłania do 02.06.2021 r. oraz późniejszej obrony ale za
maksymalnie połowę punktów możliwych do zdobycia.

Podróżnik

Stwórz aplikację, która posłuży udokumentowaniu podróży po świecie. Aplikacja powinna
umożliwiać zrobienie zdjęcia[2p], na które nałożony zostanie napis zawierający nazwę
miejscowości i kraju, w którym zostało to zdjęcie zrobione oraz datę[3p]. Do zdjęcia
użytkownik powinien móc dodać krótką notatkę < 500 znaków[2p].
Zdjęcia powinny być przechowywane w pamięci wewnętrznej aplikacji, a notatki w lokalnej
bazie danych. Aplikacja powinna oferować możliwość przeglądania zdjęć, a po wybraniu
danego zdjęcia powinna zostać wyświetlona dodana do niego notatka[2p].
Dodaj mechanizm, który będzie sprawdzał, czy użytkownik znajduje się w obrębie 1 km od
lokalizacji zrobienia zdjęcia. Jeśli tak, powinien otrzymać powiadomienie o takiej sytuacji z
możliwością obejrzenia zdjęcia oraz odczytania notatki
[3p].
Aplikacja powinna posiadać również ekran ustawień, w którym użytkownik może zmienić
kolor/rozmiar napisu na zdjęciu oraz promień okręgu działania powiadomień (domyślnie 1
km)[2p].

