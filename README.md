# Task10
Number 19
>Разбить набор треугольников, заданных тремя вершинами, на подмножества
подобных треугольников (если для какого-то треугольника не будет найдено подобных
треугольников, то этот треугольник будет образовывать подмножество из одного
треугольника).
Полученный ответ представить в виде структуры данных List<List<Triangle>> (или
Triangle[][], но это менее удобно).
## Console arguments
Console app arguments format: 
``` bash
java task10.console.Main <input file> <output file>
```
Where `<input file>` must exist and `<output file>` will be created if not exist
## Input file
Input file must contain lines in format "x y; x y; x y":
``` text
0.0 0.0; 0.0 5.0; 10.0 0.0
0.0 0.0; 0.0 10.0; 20.0 0.0
0.0 0.0; 0.0 789.0; 10.0 0.0
0.0 0.0; 0.0 5.0; 10.544 0.0
0.0 0.0; 0.0 5.0; 44.0 0.0
0.0 0.0; 0.0 5.0; 0.1 0.0
0.0 0.0; 0.0 5.0; 19.0 19.0
0.0 0.0; 0.0 500.0; 1000.0 0.0
0.0 0.0; 0.0 394.5; 5.0 0.0
