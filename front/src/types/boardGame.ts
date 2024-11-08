import { UUID } from "crypto"
import { Party } from "./party"

export type BoardGame = {
  id: UUID
  name: string
  parties?: Party[]
}
