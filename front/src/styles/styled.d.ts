/* eslint @typescript-eslint/no-empty-interface: "off" */
/* cria tipagem para o theme (ex: conseguir encontrar theme.colors..) */

import 'styled-components'

import theme from './theme'

export type Theme = typeof theme

declare module 'styled-components' {
  export interface DefaultTheme extends Theme {}
}
