import { UUID } from "crypto"
import { Party } from "./party"

export type Type = {
  id: UUID
  name: string
  parties: Party[]
}
