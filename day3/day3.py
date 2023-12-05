lines = []
with open("./day3/input.txt") as file:
    for line in file:
        lines.append(line.rstrip())


grid =  []

for line in lines:
    row = []
    parts = list(line)
    for item in parts:
        row.append(item)
    grid.append(row)

def hasPart(grid, x, y, dx, dy):

    width = len(grid)
    height = len(grid[0])

    if ((x+dx >= 0 and x+dx < width) and
        (y+dy >= 0 and y+dy < height)):
        possiblePart = grid[x+dx][y+dy]
        isPart = not possiblePart.isdigit() and not possiblePart == '.' and possiblePart != 'X'
        return isPart

    return False

sum = 0

tempSum = ""
adjacentPart = False

for i in range(0, len(lines)):
    for j in range(0, len(lines)):

        item = grid[i][j]

        t = hasPart(grid, i,j, 0, 1)
        b = hasPart(grid, i,j, 0, -1)
        l = hasPart(grid, i,j, -1, 0)
        r = hasPart(grid, i,j, 1, 0)

        tl = hasPart(grid, i,j, -1, -1)
        tr = hasPart(grid, i,j, -1, 1)
        bl = hasPart(grid, i,j, 1, -1)
        br = hasPart(grid, i,j, 1, 1)

        adjacentPart = item.isdigit() and (adjacentPart or t or b or l or r or tl or tr or bl or br)
        nextIsNumber = (j + 1 < len(grid)) and grid[i][j+1].isdigit()


        if(item.isdigit()):
            tempSum += item
            # grid[i][j] = "X"

        if (item.isdigit() and adjacentPart and not nextIsNumber):
            sum += int(tempSum)
            print(sum)

            if (sum == 55593):
                print("asd")

            tempSum = ""
            adjacentPart = False

        if (not nextIsNumber):
            tempSum = ""
            adjacentPart = False


print(sum)