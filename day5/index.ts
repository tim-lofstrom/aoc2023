import { inRange, reduce, first, tail, last, initial, map, sortBy } from 'lodash'
import { example } from './example';
import { input } from './input';

function extractNumbers(line: string): [number, number, number] {
    return line.split(' ').map(Number) as [number, number, number];
}

function extractSeeds(line: string): number[] {
    return line.split(' ').filter(Number).map(Number);
}

function destinationSum(seed: number, source: number, dest: number): number {
    return (seed - source) + dest;
}

function findSeedInRange(seed: number, [dest, source, length]: [number, number, number]): number {
    return inRange(seed, source, source + length) ? destinationSum(seed, source, dest) : seed
}

function reduceOverSet(seedtosoil: string[] | undefined, firstSeed: number) {
    return reduce(seedtosoil, (acc, line): number => {
        return acc == firstSeed ? findSeedInRange(firstSeed, extractNumbers(line)) : acc;
    }, firstSeed);
}

function calcSmallestLocation(seedList: string, almanac: string[][]) {
    const seeds = extractSeeds(seedList);
    let locations = map(seeds, seed => {
        return reduce(almanac, (acc, set) => {
            let dest = reduceOverSet(set, acc);
            return dest
        }, seed);
    });

    return first(sortBy(locations))
}

function calcSeeds(input: string[]) {
    let seeds = first(input) ?? '';
    let almanac = reduce(tail(input), (acc: string[][], line) => {
        return line.includes('map:') ?
            [...acc, []] :
            [...initial(acc), [...(last(acc) ?? []), line]];
    }, []);
    return calcSmallestLocation(seeds, almanac);
}

console.log('Part 1');
console.log("Example: " + calcSeeds(example));
console.log("Input: " + calcSeeds(input));
