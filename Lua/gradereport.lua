--Author: Sean Poston
--Date: 10/24/2020
--Purpose: To calculate grades in this class given a specifically formatted document
function printSectionGrade(array, type, weight)
    studentPossiblePoints = 0.0
    studentGottenPoints = 0.0
    totalPossiblePoints = 0.0
    totalGottenPoints = 0.0
    --Print desired format
    io.write(type, "         (", weight, "%)\n")
    io.write("==============================\n")
    for i = 1, #array do
        studentPossiblePoints = (tonumber(string.sub(array[i], 41, 43)) + .0)
        totalPossiblePoints = totalPossiblePoints + studentPossiblePoints
        studentGottenPoints = (tonumber(string.sub(array[i], 55, 56)) + .0)
        totalGottenPoints = totalGottenPoints + studentGottenPoints 
        io.write(string.sub(array[i], 1, 20))
        io.write(studentGottenPoints, "/", studentPossiblePoints, "   ")
        io.write((studentGottenPoints / studentPossiblePoints) * 100, "%\n")
    end
    io.write("==============================\n")
    io.write("                    ", totalGottenPoints, "/")
    io.write(totalPossiblePoints, "   ")
    io.write((totalGottenPoints / totalPossiblePoints) * 100, "%\n\n")
end

function printFinalGrades(filecontents)
    print()
    --Get Current Possible Points and Current Points
    studentPossiblePoints = 0.0
    studentGottenPoints = 0.0
    for i=2, filelines do
        studentPossiblePoints = studentPossiblePoints + (tonumber(string.sub    (filecontents[i], 41, 43)) + .0)
        studentGottenPoints = studentGottenPoints + (tonumber(string.sub(filecontents[i], 55, 57)) + .0)
    end

    --Print out Current Grade and Possible Grades
    io.write("Current Grade: ", math.floor((studentGottenPoints / studentPossiblePoints) * 100), "%\n")
    io.write("Mimimum Possible Grade: ", math.floor((studentGottenPoints / totalpoints) * 100), "%\n")
    maxPossGrade = math.floor((((totalpoints - (studentPossiblePoints - studentGottenPoints)) / totalpoints )) * 100)
    io.write("Maximum Possible Grade: ", maxPossGrade, "%\n")
end

function main()
    filecontents = {}
    groupProjects = {}
    homework = {}
    programming = {}
    quizzes = {}

    io.write("Enter filename: ")
    filename = io.read("*line")

    if io.input(filename) then
        file = io.input(filename)
    end

    print()
    filelines = 0

    --Read number of lines in the file
    while io.read("*line") do
        filelines = filelines + 1
    end

    --Initialize array to read lines
    for i=1, filelines do
        filecontents[i] = 0
    end

    --Reset file to read lines from beginning
    io.input():close()
    file = io.input(filename)

    --Read totalpoints at beginning of the file
    totalpoints = io.read("*line")
    filecontents[1] = totalpoints

    --Read file line by line, store each line as an element
    for i=2, filelines do
        filecontents[i] = io.read("*line")
    end

    --Close file
    io.input():close()

    --Partition types of homework to arrays
    for i = 2, filelines do
        --Get the Type of assignment, and trim whitespace
        s = string.gsub(string.sub(filecontents[i], 21, 33), "%s+", "")
        if (s == "GroupProject") then
            table.insert(groupProjects, filecontents[i])
        elseif (s == "Homework") then
            table.insert(homework, filecontents[i])
        elseif (s == "Programming") then
            table.insert(programming, filecontents[i])
        elseif (s == "Quizzes") then
            table.insert(quizzes, filecontents[i])
        end
    end

    io.write("Student: ", filename, "\n")
  
    printSectionGrade(groupProjects, "Group Projects", 5)
    printSectionGrade(homework, "Homework", 23)
    printSectionGrade(programming, "Programming", 47)
    printSectionGrade(quizzes, "Quizzes", 23)

    printFinalGrades(filecontents)
end

main()