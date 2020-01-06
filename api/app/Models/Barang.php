<?php namespace App\Models;

use App\Entities\Barang as EntitiesBarang;
use ICI4\Model;

class Barang extends Model {
    protected $table = 'barang';
    protected $allowedFields = [
        'nama', 'harga'
    ];
    protected $returnType = EntitiesBarang::class;
}