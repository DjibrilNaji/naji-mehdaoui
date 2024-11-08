import { UUID } from "crypto"
import { PartyVideoGame } from "./partyVideoGame"

export type VideoGame = {
  id: UUID
  name: string
  partyVideoGames: PartyVideoGame[]
}
