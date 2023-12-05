# Svar:        74528807
from functools import reduce

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

def partSum(grid, x, y, dx, dy):

    width = len(grid)
    height = len(grid[0])

    if ((x+dx >= 0 and x+dx < width) and
        (y+dy >= 0 and y+dy < height)):
        item = grid[x+dx][y+dy]
        isPart = item.isdigit()

        sum = ""

        if (isPart and not grid[x+dx][y+dy] == 'X'):
            sum += item
            
            nextIsNumber = (y+dy + 1 < len(grid)) and grid[x+dx][y+dy+1].isdigit()
            stepY = 1
            while nextIsNumber:
                sum += grid[x+dx][y+dy+stepY]
                grid[x+dx][y+dy+stepY] = 'X'
                stepY +=1
                nextIsNumber = (y+dy + stepY < len(grid)) and grid[x+dx][y+dy+stepY].isdigit()

            prevIsNumber = (y+dy + 1 >= 0) and grid[x+dx][y+dy-1].isdigit()

            stepY = -1
            while prevIsNumber:
                sum = grid[x+dx][y+dy+stepY] + sum
                grid[x+dx][y+dy+stepY] = 'X'
                stepY -= 1
                prevIsNumber = (y+dy + stepY >= 0) and grid[x+dx][y+dy+stepY].isdigit()

            return int(sum)
        return 0

    return 0

total_sum = 0

tempSum = ""
adjacentPart = False

for i in range(0, len(lines)):
    for j in range(0, len(lines)):

        item = grid[i][j]
        
        if (item == "*"):
            t = partSum(grid, i,j, 0, 1)
            b = partSum(grid, i,j, 0, -1)
            l = partSum(grid, i,j, -1, 0)
            r = partSum(grid, i,j, 1, 0)

            tl = partSum(grid, i,j, -1, -1)
            tr = partSum(grid, i,j, -1, 1)
            bl = partSum(grid, i,j, 1, -1)
            br = partSum(grid, i,j, 1, 1)
            
            adcanentSum = t * b * l * r * tl * tr * bl * br

            summan = [t , b , l , r, tl, tr, bl, br]

            filtered_list = [ i for i in summan if i != 0]

            if (len(filtered_list) == 2):

                gear_sum = reduce((lambda x, y: x * y), filtered_list)

                total_sum += gear_sum
            

print(total_sum)