--Author: Sean Poston
--Date: 10/12/2020 11:34 PM
--Purpose: To complete quicksort
--Notes:
-- I always like to use the Lomuto partition scheme quicksort, but
--  that requires recursion, which is only allowed in specific 
--  scenarios in Lua (namely closed tail recursion).
--  I had to find this iterative (which was written in C), and convert
--  it to Lua, which proved fairly easy.

function quicksort (list, n)
-- Iterative quicksort I found here:
-- http://alienryderflex.com/quicksort/
-- list is the array to be sorted
-- n is elements in the array
    beg = {}
    final = {}
    i = 1

    beg[1] = 1
    final[1] = n + 1

    while (i >= 1) do
        L = beg[i]
        R = final[i] - 1
        if (L < R) then
            piv = list[L]
            if (i == n * 50) then return 0 end
            while (L < R) do
                while ((list[R] >= piv) and (L < R)) do
                    R = R - 1
                end
                if (L < R) then 
                    list[L] = list[R]
                    L = L + 1
                end
                while ((list[L] <= piv) and (L < R)) do
                    L = L + 1
                end
                if (L < R) then
                    list[R] = list[L]
                    R = R - 1
                end
            end

            list[L] = piv
            beg[i + 1] = L + 1
            final[i + 1] = final[i]
            final[i] = L
            i = i + 1
        else
            i = i - 1
        end
    end
    return 1
end

function main ()
    --Declare array and get user input
    list = {}
    for i = 1, 10 do
        io.write("Enter number ", i, ": ")
        list[i] = io.read("*n")
    end
    --Print the Unsorted array
    for i = 1, 10 do
        io.write("Unsorted list ", i, ": ", list[i], "\n")
    end

    --Run quicksort function
    quicksortBool = quicksort(list, #list)

    --Print sorted array
    if quicksortBool == 1 then
        for i = 1, 10 do
            io.write("Sorted list ", i, ": ", list[i], "\n")
        end
    else
        io.write("The quicksort was unsuccessful.")
    end
end
main()