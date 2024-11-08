import { Address } from "./address"
import { BoardGame } from "./boardGame"
import { Photo } from "./photo"
import { Type } from "./type"
import { User } from "./user"
import { VideoGame } from "./videoGame"

export type Party = {
  id: string
  name: string
  count_people: number
  max_people: number
  start_timestamp: Date
  end_timestamp: Date
  publication: Date
  price: number
  bring_something: boolean
  type?: Type
  address?: Address
  videoGames?: VideoGame[]
  boardGames?: BoardGame[]
  photos: Photo[]
  creator?: User
  invitedUsers: User[]
}

export type PartyForm = {
  name: string
  count_people: number
  max_people: number
  start_timestamp: Date
  end_timestamp: Date
  publication: Date
  price: number
  bring_something: boolean
  type: { id: string }
  address: { id: string }
  photos: Photo[]
  creator: { id: string }
}
