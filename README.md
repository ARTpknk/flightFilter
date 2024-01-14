# FlightFilter 
В ТЗ указано, что может быть много правил фильтрации. Поэтому создан класс FlightFilterSpecification, с помощью которого можно выбрать нужные нам фильтрации. То есть с помощью одного запроса можно применить несколько фильтров,
Чтобы отфильтровать полёты, необходимо создать объект класса FlightFilterSpecification и отправить его в FlightController.
### FlightFilterSpecification имеет 5 фильтров:
1) LocalDateTime theEarliestTimeSpotForDeparture - Если нужно убрать полёты, которые отправляются до определённого момента времени.
   Например, чтобы убрать все полёты, которые уже отправились, то theEarliestTimeSpotForDeparture определяем
   как LocalDateTime.now();
2) boolean areFlightsWithArrivalBeforeDepartureRemoving - Если нужно удалить неправильные полёты, где посадка раньше, чем вылет;
3) Duration maxConnectionTime - можно указать максимальное время для пересадок;

В программе кроме указанных в ТЗ требований добавлены две дополнительные фильтрации:
4) LocalDate departureDate - отправление в
   определённую дату;
5) Integer maxAmountOfConnections - максимальное количество пересадок.

#### Если какой-то фильтр не нужен, то отмечаем его как null (или false если boolean).
