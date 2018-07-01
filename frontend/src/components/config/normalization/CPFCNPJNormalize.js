/**
 * Recebe o campo de cpfCnpj do formulário e formata nos seguintes padrões:
 * ###.###.###-## ou ##.###.###/####-##
 * # representa um número entre 0 e 9
 *
 * @param value estado final
 * @param previousValue estado imediatamente anterior ao final
 * @returns {*}
 */
export const normalizarCPFCNPJ = (value, previousValue) => {
  if (!value) {
    return value;
  }
  const onlyNums = value.replace(/[^\d]/g, '');

  /* trunca CNPJ */
  if (onlyNums.length >= 14) {
    return `${onlyNums.slice(0, 2)}.${onlyNums.slice(2, 5)}.${onlyNums.slice(5, 8)}/${onlyNums.slice(8, 12)}-${onlyNums.slice(12, 14)}`;
  }
  if (!previousValue || value.length > previousValue.length) {
    // typing forward
    if (onlyNums.length === 4) {
      return `${onlyNums.slice(0, 3)}.${onlyNums.slice(3, 4)}`;
    }
    if (onlyNums.length === 7) {
      return `${onlyNums.slice(0, 3)}.${onlyNums.slice(3, 6)}.${onlyNums.slice(6, 7)}`;
    }
    if (onlyNums.length === 10) {
      return `${onlyNums.slice(0, 3)}.${onlyNums.slice(3, 6)}.${onlyNums.slice(6, 9)}-${onlyNums.slice(9, 10)}`;
    }
    if (onlyNums.length > 11) {
      if (onlyNums.length === 12) {
        return `${onlyNums.slice(0, 2)}.${onlyNums.slice(2, 5)}.${onlyNums.slice(5, 8)}/${onlyNums.slice(8, 12)}`;
      }
      return `${onlyNums.slice(0, 2)}.${onlyNums.slice(2, 5)}.${onlyNums.slice(5, 8)}/${onlyNums.slice(8, 12)}-${onlyNums.slice(12, 13)}`;
    }
  }

  if (onlyNums.length <= 3) {
    return onlyNums;
  }

  if (onlyNums.length <= 6) {
    return `${onlyNums.slice(0, 3)}.${onlyNums.slice(3, 6)}`;
  }

  if (onlyNums.length <= 9) {
    return `${onlyNums.slice(0, 3)}.${onlyNums.slice(3, 6)}.${onlyNums.slice(6, 9)}`;
  }

  if (onlyNums.length <= 11) {
    return `${onlyNums.slice(0, 3)}.${onlyNums.slice(3, 6)}.${onlyNums.slice(6, 9)}-${onlyNums.slice(9, 11)}`;
  }

  if (onlyNums.length > 11 && onlyNums.length < 14) {
    if (onlyNums.length === 13) {
      return `${onlyNums.slice(0, 2)}.${onlyNums.slice(2, 5)}.${onlyNums.slice(5, 8)}/${onlyNums.slice(8, 12)}-${onlyNums.slice(12, 13)}`;
    }
    return `${onlyNums.slice(0, 2)}.${onlyNums.slice(2, 5)}.${onlyNums.slice(5, 8)}/${onlyNums.slice(8, 12)}`;
  }
  return onlyNums;
};

/* ###.###.###-## */
export const normalizarCPF = (value, previousValue) => {
  if (!value) {
    return value;
  }
  const onlyNums = value.replace(/[^\d]/g, '');

  /* trunca CPF */
  if (onlyNums.length >= 11) {
    return `${onlyNums.slice(0, 3)}.${onlyNums.slice(3, 6)}.${onlyNums.slice(6, 9)}-${onlyNums.slice(9, 11)}`;
  }

  if (!previousValue || value.length > previousValue.length) {
    // typing forward
    if (onlyNums.length === 4) {
      return `${onlyNums.slice(0, 3)}.${onlyNums.slice(3, 4)}`;
    }
    if (onlyNums.length === 7) {
      return `${onlyNums.slice(0, 3)}.${onlyNums.slice(3, 6)}.${onlyNums.slice(6, 7)}`;
    }
    if (onlyNums.length === 10) {
      return `${onlyNums.slice(0, 3)}.${onlyNums.slice(3, 6)}.${onlyNums.slice(6, 9)}-${onlyNums.slice(9, 10)}`;
    }
  }

  if (onlyNums.length <= 3) {
    return onlyNums;
  }

  if (onlyNums.length <= 6) {
    return `${onlyNums.slice(0, 3)}.${onlyNums.slice(3, 6)}`;
  }

  if (onlyNums.length <= 9) {
    return `${onlyNums.slice(0, 3)}.${onlyNums.slice(3, 6)}.${onlyNums.slice(6, 9)}`;
  }

  if (onlyNums.length < 11) {
    return `${onlyNums.slice(0, 3)}.${onlyNums.slice(3, 6)}.${onlyNums.slice(6, 9)}-${onlyNums.slice(9, 11)}`;
  }
  return onlyNums;
};

/* ##.###.###/####-## */
export const normalizarCNPJ = (value, previousValue) => {
  if (!value) {
    return value;
  }
  const onlyNums = value.replace(/[^\d]/g, '');

  /* trunca CNPJ */
  if (onlyNums.length >= 14) {
    return `${onlyNums.slice(0, 2)}.${onlyNums.slice(2, 5)}.${onlyNums.slice(5, 8)}/${onlyNums.slice(8, 12)}-${onlyNums.slice(12, 14)}`;
  }
  if (!previousValue || value.length > previousValue.length) {
    // typing forward
    if (onlyNums.length === 3) {
      return `${onlyNums.slice(0, 2)}.${onlyNums.slice(2, 3)}`;
    }
    if (onlyNums.length === 6) {
      return `${onlyNums.slice(0, 2)}.${onlyNums.slice(2, 5)}.${onlyNums.slice(5, 6)}`;
    }
    if (onlyNums.length === 9) {
      return `${onlyNums.slice(0, 2)}.${onlyNums.slice(2, 5)}.${onlyNums.slice(5, 8)}/${onlyNums.slice(8, 9)}`;
    }
    if (onlyNums.length === 13) {
      return `${onlyNums.slice(0, 2)}.${onlyNums.slice(2, 5)}.${onlyNums.slice(5, 8)}/${onlyNums.slice(8, 12)}-${onlyNums.slice(12, 13)}`;
    }
  }
  if (onlyNums.length <= 2) {
    return onlyNums;
  }
  if (onlyNums.length <= 3) {
    return `${onlyNums.slice(0, 2)}.${onlyNums.slice(2, 3)}`;
  }

  if (onlyNums.length <= 5) {
    return `${onlyNums.slice(0, 2)}.${onlyNums.slice(2, 5)}`;
  }

  if (onlyNums.length <= 8) {
    return `${onlyNums.slice(0, 2)}.${onlyNums.slice(2, 5)}.${onlyNums.slice(5, 8)}`;
  }

  if (onlyNums.length <= 12) {
    return `${onlyNums.slice(0, 2)}.${onlyNums.slice(2, 5)}.${onlyNums.slice(5, 8)}/${onlyNums.slice(8, 12)}`;
  }

  if (onlyNums.length <= 14) {
    return `${onlyNums.slice(0, 2)}.${onlyNums.slice(2, 5)}.${onlyNums.slice(5, 8)}/${onlyNums.slice(8, 12)}-${onlyNums.slice(12, 14)}`;
  }
  return onlyNums;
};
