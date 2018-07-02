
/* R$ ###.###.###,## */
export const normalizarDinheiro = (value, previousValue) => {
    if (!value) {
        return value;
    }
    let onlyNums = value.replace(/[^\d]/g, '');
    const tam = onlyNums.length;

    while (onlyNums.slice(0, 1) == 0) {
        onlyNums = onlyNums.slice(1, tam);
    }
    if (onlyNums.length >= 11) {
        return `R$ ${onlyNums.slice(0, 3)}.${onlyNums.slice(3, 6)}.${onlyNums.slice(6, 9)},${onlyNums.slice(9, 11)}`;
    }

    if (onlyNums.length == 1) {
        return `R$ 0,0` + onlyNums;
    }

    if (onlyNums.length == 2) {
        return `R$ 0,` + onlyNums;
    }

    if (onlyNums.length <= 5) {
        return `R$ ` + onlyNums.slice(0, onlyNums.length - 2) + `,` + onlyNums.slice(onlyNums.length - 2, onlyNums.length);
    }

    if (onlyNums.length <= 8) {
        return `R$ ` + onlyNums.slice(0, onlyNums.length - 5) + `.` + onlyNums.slice(onlyNums.length - 5, onlyNums.length - 2) + `,` + onlyNums.slice(onlyNums.length - 2, onlyNums.length);
    }

    if (onlyNums.length <= 11) {
        return `R$ ` + onlyNums.slice(0, onlyNums.length - 8) + `.` + onlyNums.slice(onlyNums.length - 8, onlyNums.length - 5) + `.` + onlyNums.slice(onlyNums.length - 5, onlyNums.length - 2) + `,` + onlyNums.slice(onlyNums.length - 2, onlyNums.length);
    }

    return `R$ ${onlyNums.slice(0, tam - 2)},${onlyNums.slice(tam - 2, tam)}`;
};