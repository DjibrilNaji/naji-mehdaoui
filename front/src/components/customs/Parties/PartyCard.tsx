import Image from "next/image"
import Link from "next/link"

import routes from "@/routes"
import { Party } from "@/types/party"

interface PartyProps {
  party: Party
}

export default function PartyCard({ party }: PartyProps) {
  return (
    <Link href={routes.party(party.id)} className="w-full p-4">
      <div className="bg-white shadow-lg rounded-lg overflow-hidden">
        <Image
          src="https://www.maisonsarchidesign.com/wp-content/uploads/2019/11/Modele-Manoir-1-R1-5.jpg"
          alt="Image de la location"
          width={500}
          height={300}
          className="w-full h-64 object-cover"
        />
        <div className="px-4 py-2">
          <h2 className="text-lg font-semibold truncate">{party.name}</h2>
          <p className="mb-2">{party.type.name}</p>

          <h2 className="text-gray-600">{party.address.city}</h2>
          <p className="text-gray-600 mb-2">
            {new Date(party.start_timestamp).toLocaleDateString("fr-FR")}
          </p>

          <p className="text-lg font-bold text-gray-800">{party.price}€</p>
          <p className="text-gray-800">Reste {party.max_people - party.count_people} place(s)</p>
        </div>
      </div>
    </Link>
  )
}
