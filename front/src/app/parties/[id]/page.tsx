"use client"
import { AvatarComponent } from "@/components/customs/Utils/AvatarComponent"
import { Spinner } from "@/components/customs/Utils/Spinner"
import { findById } from "@/services/PartyService"
import { formatAddress } from "@/utils/formatAddress"

import { useQuery } from "@tanstack/react-query"
import Image from "next/image"
import Link from "next/link"
import { redirect, useParams } from "next/navigation"

export default function Party() {
  const params = useParams<{ id: string }>()

  const { data, error, isPending } = useQuery({
    queryKey: ["party", params.id],
    queryFn: async () => await findById(params.id)
  })

  if (isPending) return <Spinner />
  if (error) redirect("/parties")

  return (
    <>
      <Link href="/parties" className="text-lg font-bold p-2">
        Retour
      </Link>

      <div className="h-full flex flex-col items-center justify-center">
        <div className="flex flex-col gap-2">
          <Image
            src="https://www.maisonsarchidesign.com/wp-content/uploads/2019/11/Modele-Manoir-1-R1-5.jpg"
            alt="Image de la location"
            width={500}
            height={300}
            className="w-94 h-94 object-cover"
          />

          <div className="mx-2 mt-4">
            <div className="flex items-center justify-between">
              <h1 className="text-3xl font-bold">{data.name}</h1>
              <p className="text-lg text-gray-600">
                {new Date(data.start_timestamp).toLocaleDateString("fr-FR")}
              </p>
            </div>

            {data.address && <p className="text-md text-gray-500">{formatAddress(data.address)}</p>}

            <p className="text-md text-gray-500">Type : {data.type?.name}</p>
            <p className="text-md text-gray-500">
              Places : {data.max_people - data.invitedUsers.length}
            </p>
            <p className="text-md text-gray-500">Prix : {data.price}€</p>
          </div>

          <div className="flex items-center gap-2 mt-4">
            <AvatarComponent />
            <span className="text-gray-700">Créateur : {data.creator?.username}</span>
          </div>

          <button
            className="mt-6 w-48 py-2 rounded-lg text-white bg-blue-500 mx-auto disabled:opacity-30"
            disabled
          >
            Participer
          </button>
        </div>
      </div>
    </>
  )
}
