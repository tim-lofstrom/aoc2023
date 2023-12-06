import { intersection, range, reduce } from 'lodash'
import { example } from './example';
import { input } from './input';

function winningPoints(line: string): number {
    const cardParts = line.split('|').map(i => i.split(' ').filter(i => i != '').map(i => Number(i)))
    return intersection(...cardParts).length;
}

function winningPointsSum(input: string[]) {
    return reduce(input, (acc, line) => {
        const parts = line.split(':')
        const winningNumbers = winningPoints(parts[1])
        return acc += winningNumbers == 0 ? 0 : 2 ** (winningNumbers - 1);
    }, 0)
}

function processScratchcard(line: string, [copiesMap, sum]: [Map<string, number>, number]): [Map<string, number>, number] {
    const parts = line.split(':')
    const id = parts[0].replace(/\D/g, '');
    const cards = 1 + (copiesMap.get(id) ?? 0);
    const points = winningPoints(parts[1])
    return [addCopies(points, id, cards, copiesMap), sum + cards]
}

function addCopies(points: number, id: string, cards: number, copiesMap: Map<string, number>): Map<string, number> {
    return reduce(range(1, points + 1), (acc, i) => {
        const nextId = (Number(id) + i).toString();
        return acc.set(nextId, cards + (acc.get(nextId) ?? 0));
    }, copiesMap);
}

function processedScratchcardsSum(input: string[]) {
    const copiesMap = new Map<string, number>();
    return reduce(input, (acc: [Map<string, number>, number], line) => processScratchcard(line, acc), [copiesMap, 0])[1];
}

console.log('Part 1');
console.log("Example: " + winningPointsSum(example));
console.log("Input: " + winningPointsSum(input));
console.log('---');
console.log('Part 2');
console.log("Example: " + processedScratchcardsSum(example));
console.log("Input: " + processedScratchcardsSum(input));
